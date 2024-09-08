ALTER USER sys IDENTIFIED BY oracle21

ALTER USER sys ACCOUNT UNLOCK;

ALTER SESSION SET CONTAINER = XEPDB1;

CREATE USER cohortdb IDENTIFIED BY oracle21;

GRANT CONNECT, RESOURCE TO cohort_db;

--Grant unlimited access to db
ALTER USER cohortdb QUOTA UNLIMITED ON USERS;

--Grant specific access to db
ALTER USER cohortdb QUOTA 100M ON USERS;

GRANT CREATE JOB TO cohortdb;

GRANT MANAGE SCHEDULER TO cohortdb;

--Log tables
CREATE TABLE COHORTDB.STATUS_CHANGE_LOG (
    LOG_ID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY,
    COHORT_APPLICANT_ID NUMBER(19,0),
    OLD_STATUS VARCHAR2(255 CHAR),
    NEW_STATUS VARCHAR2(255 CHAR),
    CHANGE_DATE TIMESTAMP,
    PRIMARY KEY (LOG_ID)
);


-- Modify the procedure to log status changes
CREATE OR REPLACE PROCEDURE update_expired_status AS
BEGIN
    -- Insert records into the log table for status changes
    INSERT INTO COHORTDB.STATUS_CHANGE_LOG (COHORT_APPLICANT_ID, OLD_STATUS, NEW_STATUS, CHANGE_DATE)
    SELECT ca.ID, ca.STATUS, 'EXPIRED', CURRENT_TIMESTAMP
    FROM COHORTDB.COHORT_APPLICANTS ca
    WHERE ca.STATUS = 'PENDING'
      AND EXISTS (
          SELECT 1
          FROM COHORTDB.COHORTS c
          WHERE ca.COHORT_ID = c.ID
            AND c.END_DATE < CURRENT_TIMESTAMP
        );

    -- Update the status of the applicants
    UPDATE COHORTDB.COHORT_APPLICANTS ca
    SET ca.STATUS = 'EXPIRED'
    WHERE ca.STATUS = 'PENDING'
      AND EXISTS (
          SELECT 1
          FROM COHORTDB.COHORTS c
          WHERE ca.COHORT_ID = c.ID
            AND c.END_DATE < CURRENT_TIMESTAMP
        );
END;


--Create scheduler job
BEGIN
    DBMS_SCHEDULER.create_job (
        job_name        => 'UPDATE_EXPIRED_STATUS_JOB',
        job_type        => 'PLSQL_BLOCK',
        job_action      => 'BEGIN update_expired_status; END;',
        start_date      => SYSTIMESTAMP,
        repeat_interval => 'FREQ=DAILY; BYHOUR=0; BYMINUTE=0; BYSECOND=0',
        enabled         => TRUE
    );
END;

--Grant access to see log
GRANT SELECT ON DBA_SCHEDULER_JOBS TO cohortdb;
GRANT SELECT ON DBA_SCHEDULER_JOB_RUN_DETAILS TO cohortdb;


--Check job log
SELECT * FROM COHORTDB.STATUS_CHANGE_LOG WHERE CHANGE_DATE >= TRUNC(SYSDATE);

--MOniter Job
SELECT job_name, start_date, repeat_interval, last_start_date, last_run_duration,  next_run_date, JOB_ACTION
FROM dba_scheduler_jobs
WHERE job_name = 'UPDATE_EXPIRED_STATUS_JOB';

SELECT *
FROM dba_scheduler_job_run_details
WHERE job_name = 'UPDATE_EXPIRED_STATUS_JOB'
ORDER BY log_date DESC;

