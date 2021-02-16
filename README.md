#PayRoll-Test-Project

Rest API


## How it works?

The program works using requests (GET, POST , PUT ) and configure JSON Body if Needed
## Usage 

In order to obtain good results :

**1> type "localhost:8080/api/v1/Employee" For Employee Request and add those listed Url for specific Service
```for Employee Requests

    
     // Get All Employees in the Database
    @GetMapping
    public List<Employee> getEmployees()
    
    // Add a new Employee into the Database , take a JSON BodyRequest of an Employee , throw exception if Gross not mentionned
    @PostMapping("/registerNewEmployee")
    public String registerNewEmployee (@RequestBody Employee employee) 
    
    // Get List of All employees in the Company , 
    @GetMapping("/getEmployeeByCompany/{company}")
    public List<Employee> getEmployeesByCompany(@PathVariable String company)
    
    // Get List of All employees in the Company in a Specific Period
    @GetMapping("/getEmployeeByCompanyAndPeriod/{company}/{period}")
    public List<Employee> getEmployeesByCompanyAndPeriod(@PathVariable String company, @PathVariable int period)
    
    // Update Gross Salary of a given employee identifier, takes a string in the BodyRequest , represent the new grossSalary to change
    @PutMapping("/updateEmployee/{id}")
    public Employee updateEmployeeSalary(@PathVariable String id, @RequestBody Double salary)
    
    // Get Employee by his/her ID
    @GetMapping("/getEmployeeById/{id}")
    public Optional<Employee> getEmployeebyId(@PathVariable String id) 
   
}

```



**2> type "localhost:8080/api/v1/PayRecord" for calculate and PayRecord Request and add those listed Url for specific service :
```for PayRecord Requests

    
    // Calculate Payroll of a Whole Company in a period
    @PostMapping("/ByCompanyByPeriod/{company}/{period}")
    public int PayRecordByCompanyByPeriod (@PathVariable String company, @PathVariable int period)
        
    
    // Calculate Payroll of a Given list of Employee
    @PostMapping("/PayRollByPeriod/{period}")
    public int PayRollByPeriod (@PathVariable int period,@RequestBody List<Employee> emp)
       
    

    // Calculate Whole Payroll of Each Existing Company in a Given Period , return a JSON Body with all the information
    @PostMapping("/GetWholePayRoll/{period}")
    public List<FullPayRoll> GetWholePayRoll (@PathVariable int period) 

```
