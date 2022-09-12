package com.example.porfoliotest.Controller;
import com.example.porfoliotest.model.Fonction;
import com.example.porfoliotest.model.User;
import com.example.porfoliotest.repository.FonctionRepository;
import com.example.porfoliotest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class PortfolioTestApplicationController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private FonctionRepository foncRepo;
    /*======================================
     Get Mappings (to show pages)
    ======================================*/
    @GetMapping("/index")
    public String Home() {

        return "index";
    }
    /*------------------------------------/ new page /--------------------------------------------*/


    @GetMapping("/page2")
    public String about(User user, Model model) {

        List<User> userList = userRepo.findAll();
        List<Fonction> FonctionList = foncRepo.findAll();
        model.addAttribute("userList",userList);
        model.addAttribute("FonctionList",FonctionList);
        return "page2";
    }
    /*------------------------------------/ new page /--------------------------------------------*/

    @GetMapping("/show/{id}")
    public String showing(@PathVariable ( value="id") Long userId, Model model) {
        User utilisateur = null;
        Optional<User> user = userRepo.findById(userId);
        List<Fonction> myFonction = foncRepo.findAll();
        model.addAttribute("myFonction",myFonction);
        System.out.println(myFonction);
        if (user.isPresent()){
            System.out.println(user);

            utilisateur = user.get();
            model.addAttribute("utilisateur",utilisateur);

        }else{
            return "show";
        }
        return "show";
    }

    /*------------------------------------/ new page /--------------------------------------------*/
    @GetMapping("/delete/{id}")
    public String supprimer(@PathVariable(value="id") Long userId, Model model) {

        Optional<User> user = userRepo.findById(userId);

        if(user.isPresent()) {

            userRepo.delete(user.get());
        }
        return "redirect:/page2";
    }
    /*  @GetMapping("/error")
      public String error() {

          return "error";
      }*/
    /*======================================
    Post Mappings (to get info from pages)
    ======================================*/
    @PostMapping("/page2")
    public String postHome(@Validated User user, BindingResult bindingResult) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!"+bindingResult.hasErrors());
        System.out.println(user.getEmail());
        if (bindingResult.hasErrors()){
            return "page2";
        }
        if (userRepo.findByEmail(user.getEmail()) != null) {
            bindingResult.addError(new FieldError("user","email","le Mail Existe déjà"));
            System.out.println(bindingResult.getFieldError());
            return "page2";
        }else {
            userRepo.save(user);

        }

        return "redirect:/page2";
    }
    @PostMapping("/show/{id}")
    public String postShowing(@PathVariable ( value="id") Long userId,@Validated User userDetail, Model model) {
        User utilisateur = null;
        Optional<User> user = userRepo.findById(userId);


        if (user.isPresent()) {


            System.out.println("dans le IF");
            utilisateur = user.get();
            System.out.println(utilisateur.toString());

            utilisateur.setNom(userDetail.getNom());
            utilisateur.setPrenom(userDetail.getPrenom());
            utilisateur.setEmail(userDetail.getEmail());
            utilisateur.setFonction(userDetail.getFonction());

            System.out.println(utilisateur.getPrenom());
            System.out.println(utilisateur.getFonction());


            userRepo.save(utilisateur);

        }

        return "redirect:/page2";
    }


    @GetMapping("/page3")
    public String aboutFonc(Fonction Fonction, Model model) {

        List<Fonction> FonctionList = foncRepo.findAll();
        model.addAttribute("FonctionList",FonctionList);
        return "page3";
    }
    /*------------------------------------/ new page /--------------------------------------------*/

    @GetMapping("/showFonc/{id}")
    public String showingFonc(@PathVariable ( value="id") Long FonctionId, Model model) {
        Fonction fonction = null;
        Optional<Fonction> Fonction = foncRepo.findById(FonctionId);
        if (Fonction.isPresent()){
            System.out.println(Fonction);

            fonction = Fonction.get();
            model.addAttribute("fonction",fonction);

        }else{
            return "showFonc";
        }
        return "showFonc";
    }

    /*------------------------------------/ new page /--------------------------------------------*/
    @GetMapping("/deleteFonc/{id}")
    public String supprimerFonc(@PathVariable(value="id") Long FonctionId, Model model) {

        Optional<Fonction> Fonction = foncRepo.findById(FonctionId);

        if(Fonction.isPresent()) {

            foncRepo.delete(Fonction.get());
        }
        return "redirect:/";
    }
    /*======================================
    Post Mappings (to get info from pages)
    ======================================*/
    @PostMapping("/page3")
    public String postHomeFonc(@Validated Fonction Fonction, BindingResult bindingResult) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!"+bindingResult.hasErrors());
        System.out.println(Fonction.getFonction_nom());

        foncRepo.save(Fonction);



        return "redirect:/page3";
    }
    @PostMapping("/showFonc/{id}")
    public String postShowingFonc(@PathVariable ( value="id") Long FonctionId,@Validated Fonction FonctionDetail, Model model) {
        Fonction fonction = null;
        Optional<Fonction> Fonction = foncRepo.findById(FonctionId);
        if (Fonction.isPresent()) {
            System.out.println("dans le IF");
            fonction = Fonction.get();
            System.out.println(fonction.toString());

            fonction.setFonction_nom(FonctionDetail.getFonction_nom());


            System.out.println(fonction.getFonction_nom());

            foncRepo.save(fonction);

        }

        return "redirect:/page3";
    }
}