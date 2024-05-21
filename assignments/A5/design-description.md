# Design description document

## Classes
* JobManager 
	* This is the class that represents the entry point to the system, and that ties the various pieces together.
* User
* Job
* CurrentJob
* JobOffer
* ComparisonSettings
* Money
* Location

## Attributes
* JobManager
	* currentJob : CurrentJob = null
	* jobs : List&lt;Job&gt; = empty list
* User
	* comparisonSettings : ComparisonSettings = new ComparisonSettings()
* Job
	* title : String = ""
	* company : String = ""
	* location : Location = new Location()
	* costOfLiving : Integer = 0
	* yearlySalary : Money
	* yearlyBonus : Money
	* weeklyTeleworkDays : Integer = 0
	* leaveTime : Integer = 0
	* gymMembershipAllowance : Money
	* isCurrent : Boolean = false
* ComparisonSettings
	* yearlySalaryWeight : Integer = 1
	* yearlyBonusWeight : Integer = 1
	* weeklyTeleworkDaysWeight : Integer = 1
	* leaveTimeWeight : Integer = 1
	* gymMembershipAllowanceWeight : Integer = 1
* Location
	* city : String = ""
	* state : String = ""

## Operations
* JobManager
	* enterCurrentJobDetails(CurrentJob) : Void
	* editCurrentJobDetails(CurrentJob) : Void
	* enterJobOfferDetails(JobOffer) : Void
	* compareJobs(Job0, Job1) : Integer
	* countJobs() : Integer
	* rankSortJobs() : Void
* User
	* adjustComparisonSettings() : Void
* Job
	* getJobScore() : Float
* ComparisonSettings
	* setYearlySalaryWeight(Integer) : Void
	* setYearlyBonusWeight(Integer) : Void
	* setWeeklyTeleworkDaysWeight(Integer) : Void
	* setLeaveTimeWeight(Integer) : Void
	* setGymMembershipAllowanceWeight(Integer) : Void

## Relationships
* JobManager
	* depends on Job and User
* User
	* depends on ComparisonSettings
* Job
	* depends on Location
* CurrentJob
	* is a Job
* JobOffer
	* is a Job

## Requirements

1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, 

`To realize these requirements, I added operations enterCurrentJobDetails, editCurrentJobDetails, and enterJobOfferDetails to the JobManager class. The JobManager class has attribute currentJob (of type CurrentJob) which gets set whenever enterCurrentJobDetails or editCurrentJobDetails is called. The JobManager class also has attribute jobs (of type List of Jobs) which gets set whenever enterCurrentJobDetails, editCurrentJobDetails, or enterJobOfferDetails is called.`

	(3) adjust the comparison settings, 

`To realize this requirement, I added operation adjustComparisonSettings to the User class. The User class has attribute comparisonSettings (of type ComparisonSettings) which gets set whenever adjustComparisonSettings is called.`

	or (4) compare job offers (disabled if no job offers were entered yet). To be precise, this functionality will be enabled if there are either (1) at least two job offers, in case there is no current job, or (2) at least one job offer, in case there is a current job.

`To realize this requirement, I added operation compareJobs to the JobManager class. This operation takes in two Job objects (Job0 & Job1) and returns an integer indicating which job scores better. (If Job0 scores better, then compareJobs returns -1; if Job1 scores bettern, then compareJobs returns 1; if their scores are equal, compareJobs returns 0.) The JobManager's attributes currentJob and jobs can be used to determine whether the compareJobs operation is enabled.`

2. When choosing to enter current job details, a user will:
a- Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job.

`To realize this requirement, the JobManager's attribute currentJob can be used to determine whether to use the operation enterCurrentJobDetails or editCurrentJobDetails.`

	b- Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

`This is not represented in my design, as it will be handled entirely within the GUI implementation.`

3. When choosing to enter job offers, a user will:
a- Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.

`To realize these requirements, I added operation enterJobOfferDetails to the JobManager class. The JobManager class has attribute jobs (of type List of Jobs) which gets set whenever enterJobOfferDetails is called.`

	b- Be able to either save the job offer details or cancel.

`This is not represented in my design, as it will be handled entirely within the GUI implementation.`

	c- Be able to (1) enter another offer, 

`To realize this requirement, the JobManager's operation enterJobOfferDetails can be used.`

	(2) return to the main menu, or 

`This is not represented in my design, as it will be handled entirely within the GUI implementation.`

	(3) compare the offer (if they saved it) with the current job details (if present).

`To realize this requirement, the JobManager's operation compareJobs can be used. The JobManager's attribute currentJob can be used to determine if a current job is present.`

4. When adjusting the comparison settings, the user can assign integer weights.

`To realize this requirement, I added operation adjustComparisonSettings to the User class. The User class has attribute comparisonSettings (of type ComparisonSettings) which gets set whenever adjustComparisonSettings is called. The class ComparisonSettings has these operations: setYearlySalaryWeight, setYearlyBonusWeight, setWeeklyTeleworkDaysWeight, setLeaveTimeWeight, & setGymMembershipAllowanceWeight.`

	If no weights are assigned, all factors are considered equal.

`To realize this requirement, in the class ComparisonSettings, there are these attributes, each with a default integer value of 1: yearlySalaryWeight, yearlyBonusWeight, weeklyTeleworkDaysWeight, leaveTimeWeight, & gymMembershipAllowanceWeight. `

5. When choosing to compare job offers, a user will:
a- Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.

`To realize this requirement, I added operation rankSortJobs to the JobManager class. This function will use another function in JobManager, compareJobs, to sort the list of jobs.`

b- Select two jobs to compare and trigger the comparison.

`To realize this requirement, the JobManager's operation compareJobs can be used.`

c- Be shown a table comparing the two jobs.

`This is not represented in my design, as it will be handled entirely within the GUI implementation.`

d- Be offered to perform another comparison or go back to the main menu.

`This is not represented in my design, as it will be handled entirely within the GUI implementation.`

6. When ranking jobs, a job’s score is computed.

`To realize this requirement, I added operation getJobScore to the Job class.`

7. The user interface must be intuitive and responsive.

`This is not represented in my design, as it will be handled entirely within the GUI implementation.`

8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

`To realize this requirement, I added all the classes, attributes, and operations necessary to satisfy all these requirements.`
