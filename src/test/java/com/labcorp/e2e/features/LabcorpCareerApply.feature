
#Author: Sabi
#Date:  June 10 2022
Feature: Test LabCorp Apply for Job Functionality

  Scenario: Test Apply for QA Automation Job
    Given user is on the "https://www.labcorp.com" page
    When user clicks on career link
    And user searches for "QA Test Automation Developer" job
    And user selects and browses the position
    And and user verifies job specific details

      | jobTitle                         | jobLocation                                      | jobId       |  descriptionOne                                                                                                             |descriptionTwo                               |descriptionThree                                                  | toolKeyWord  |
      | QA Test Automation Developer     | Durham, North Carolina, United States of America | 21-90223_RM |  The right candidate for this role will participate in the test automation technology development and best practice models  | Prepare test plans, budgets, and schedules. |5+ years of experience in QA automation development and scripting.| Selenium     |

    And user clicks on on Apply Now  and confirms Job title, Job location, job id and description
      | jobTitle                         | jobLocation             | jobId       |  descriptionOne                                                                                                             |
      | QA Test Automation Developer     | Durham NC-8 Moore Dr    | 21-90223_RM |  The right candidate for this role will participate in the test automation technology development and best practice models  |

    Then user returns to job search