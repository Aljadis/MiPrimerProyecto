/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import service.UserService;

/**
 *
 * @author Angel A. Díaz Piña
 */
public class UserController extends SimpleFormController {
    
    private UserService addUserService;

    public void setAddUserService(UserService addUserService) {
        this.addUserService = addUserService;
    }
    
    /*********************************************************************************/
//    private List<User> userList = new ArrayList<User>(); 
//	
//	@RequestMapping(value="/AddUserView.htm",method=RequestMethod.GET)
//	public String showForm(){
//		return "AddUserView";
//	}
//	
//	@RequestMapping(value="/AddUserView.htm",method=RequestMethod.POST)
//	public @ResponseBody String addUser(@ModelAttribute(value="user") User user, BindingResult result ){
//		String returnText;
//		if(!result.hasErrors()){
//			userList.add(user);
//			returnText = "User has been added to the list. Total number of users are " + userList.size();
//		}else{
//			returnText = "Sorry, an error has occur. User has not been added to list.";
//		}
//		return returnText;
//	}
//
//	@RequestMapping(value="/ShowUsersView.htm")
//	public String showUsers(ModelMap model){
//		model.addAttribute("Users", userList);
//		return "ShowUsersView";
//	}
    
    
    public UserController() {
        //Initialize controller properties here or 
        //in the Web Application Context

        setCommandClass(User.class);
        setCommandName("user");
        setSuccessView("showUsersView");
        setFormView("userView");
    }
    
   

    //Use onSubmit instead of doSubmitAction 
    //when you need access to the Request, Response, or BindException objects
    
    @Override
    protected ModelAndView onSubmit(
            HttpServletRequest request, 
            HttpServletResponse response, 
            Object command, 
            BindException errors) throws Exception {
        
        User user = (User)command;
        
        ModelAndView mv = new ModelAndView(getSuccessView());
        mv.addObject("cantUsuarios", addUserService.adicionarUser(user));
        mv.addObject("listaUsuarios", addUserService.getListaUsers());
        mv.addObject("listaDepartamentos", addUserService.getDepartamentoList());
        return mv;
    }
    
}
