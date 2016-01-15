package com.mycompany.journal.controllers;

/*@Controller
@RequestMapping("/")*/
public class ServiceController {
   /* @Autowired
    private ServicesRepository departmentRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String showDepartments(ModelMap model) {
        List<Department> departmentList = departmentRepository.findAll();
        model.addAttribute("departmentList", departmentList);

        return "department/department_table";
    }

    @RequestMapping(value = "createDepartmentStart", method = RequestMethod.GET)
    public String createDepartmentStart(ModelMap model) {

        return "department/department_create";
    }

    @RequestMapping(value = "createDepartment", method = RequestMethod.GET)
    public String createDepartment(
            @RequestParam(value = "departmentName", required = false) String departmentName,
            ModelMap model) {

        departmentRepository.saveAndFlush(new Department(departmentName));


        return "redirect:/";
    }*/
}
