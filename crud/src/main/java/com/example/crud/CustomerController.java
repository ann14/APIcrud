package com.example.crud;

import com.example.crud.Repository.CustomerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/")
@Api("crud")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @PostMapping(path="/api/c")
    @ApiOperation("create a user")
    public @ResponseBody
    String addNewUser (@RequestParam String fname, @RequestParam String lname) {

        Customer n = new Customer(fname, lname);
        customerRepository.save(n);
        return "Saved";
    }
    @GetMapping(path="/customer")
    @ApiOperation("search all customer")
    public @ResponseBody Iterable<Customer> getAllUsers() {

        return customerRepository.findAll();
    }
    @GetMapping(path="/api/r/{id}")
    @ApiOperation("read a user")
    @ResponseBody
    public String read(@PathVariable(name = "id") String id ){
        Customer cus=customerRepository.findById(Long.parseLong(id));

        return cus.getId()+"    "+cus.getFirstName()+"    "+cus.getLastName();
    }
    @PostMapping(path="/api/u/{id}")
    @ApiOperation("update a user")
    @ResponseBody
    public String update(@PathVariable(name = "id") String id,@RequestParam String fname, @RequestParam String lname){
        Customer cus=customerRepository.findById(Long.parseLong(id));
        cus.setFirstName(fname);
        cus.setLastName(lname);
        customerRepository.save(cus);
        return "updated";
    }
    @PostMapping(path="/api/d/{id}")
    @ApiOperation("delete a user")
    @ResponseBody
    public String delete(@PathVariable(name = "id") String id){
        customerRepository.deleteById(Long.parseLong(id));
        return "deleted";
    }
}
