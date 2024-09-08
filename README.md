Project Description:

This proof of concept (POC) involves developing a REST-based application integrated with an Oracle Database. The primary objective is to demonstrate the ability to create and manage database scheduler jobs that automate the updating of application data based on specific conditions.

Key Features:

Database Scheduler Job Creation:

Design and implement a database scheduler job using Oracle’s DBMS_SCHEDULER package.
The job will run daily at midnight to ensure timely updates.
Status Management:

The job will automatically update the status of records in the COHORT_APPLICANTS table to 'EXPIRED' when certain conditions are met.
Specifically, the job will check if the cohort's END_DATE has passed and if the applicant’s current status is 'PENDING'.
Integration with REST-based Application:

The REST application will interface with the Oracle Database to interact with the COHORT_APPLICANTS and COHORTS tables.
The application will trigger and manage the scheduler job through its RESTful API endpoints.
Logging and Reporting:

Implement a logging mechanism to track the status changes made by the scheduler job.
Provide reporting capabilities to view and analyze the changes to the COHORT_APPLICANTS records.
Objective:

To validate the process of scheduling and automating database tasks within an Oracle environment.
To demonstrate the integration between a REST-based application and Oracle DB for effective data management and job scheduling.
Benefits:

Automates routine data updates, reducing manual intervention and errors.
Provides a scalable solution for managing status changes based on dynamic conditions.
Enhances the ability to monitor and report on data changes effectively.
