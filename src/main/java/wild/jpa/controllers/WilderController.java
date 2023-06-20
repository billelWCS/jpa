package wild.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wild.jpa.models.Wilder;
import wild.jpa.repositories.WilderRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class WilderController {

    @Autowired
    WilderRepository wilderRepo;

    @RequestMapping("/create")
    public Wilder createWilder (String name, String email, String category){
        Wilder wilder = new Wilder(name, email, category);
        return wilderRepo.save(wilder);
    }

    @PostMapping("/wilders/save")
    public Wilder createWilder2 (@RequestBody Wilder wilder){
        return wilderRepo.save(wilder);
    }

    @PostMapping("/wilders/create")
    public Wilder createWilder3 (@RequestBody Map<String,String> requestMap){
        Wilder wilder = new Wilder(
                requestMap.get("name"),
                requestMap.get("courriel"),
                requestMap.get("category")
        );
        return wilderRepo.save(wilder);
    }

    @GetMapping("/wilders")
    public List<Wilder> getAllWilders (){
        return wilderRepo.findAll();
    }

    @GetMapping("/wilders/{id}")
    public ResponseEntity<?> getWilderById (@PathVariable Long id){
        Optional<Wilder> reslut = wilderRepo.findById(id);
        if (reslut.isPresent()){
            return new ResponseEntity<Wilder>(reslut.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("wilder not found" , HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/wilders/by-category/{category}")
    public List<Wilder> getWildersByCategory (@PathVariable String category){
        return wilderRepo.findWilderByCategory(category);
    }

    @GetMapping("/wilders/by-email/{email}")
    public List<Wilder> getWildersByEmail (@PathVariable String email){
        return wilderRepo.findWilderByEmail(email);
    }

    @DeleteMapping("/wilders/{id}")
    public void deleteWilderById (@PathVariable Long id){
         wilderRepo.deleteById(id);
    }

}
