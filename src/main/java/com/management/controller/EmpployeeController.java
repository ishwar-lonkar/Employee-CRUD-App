package com.management.controller;


import com.management.entity.Employee;
import com.management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;


@Controller
public class EmpployeeController {


    @Autowired
    private EmployeeService empServ;

    @GetMapping("/")
    public String home(Model mod){
        return findPagination(0, mod);
    }
    
    @GetMapping("/addemployee")
    public String addEmployeeForm(){
        return "addEmployee";
    }

    @PostMapping("/register")
    public String empRegister(@ModelAttribute Employee emp, HttpSession session ){
        empServ.addEmployee(emp);
        session.setAttribute("msg", "Emplyoee Added Sucessfully..");
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model m) {
        Employee e = empServ.getEMpById(id);
        m.addAttribute("emp", e);
        return "edit";
    }

    @PostMapping("/update")
    public String updateEmp(@ModelAttribute Employee e, HttpSession session) {
        empServ.addEmployee(e);
        session.setAttribute("msg", "Emp Data Update Sucessfully..");
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEMp(@PathVariable int id, HttpSession session) {

        empServ.deleteEMp(id);
        session.setAttribute("msg", "Emp Data Delete Sucessfully..");
        return "redirect:/";
    }

    @GetMapping("/page/{pageno}")
    public String findPagination(@PathVariable int pageno, Model m) {

        Page<Employee> emplist = empServ.getEMpByPaginate(pageno, 2);
        m.addAttribute("emp", emplist);
        m.addAttribute("currentPage", pageno);
        m.addAttribute("totalPages", emplist.getTotalPages());
        m.addAttribute("totalItem", emplist.getTotalElements());
        return "index";
    }

}
