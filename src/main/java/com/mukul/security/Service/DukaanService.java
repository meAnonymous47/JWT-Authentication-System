package com.mukul.security.Service;
import java.util.List;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Service;
import com.mukul.security.Model.DukaanItem;
import com.mukul.security.Repository.DukaanRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class DukaanService {
    
    private final DukaanRepository repo;
    DukaanService(DukaanRepository repo){
        this.repo = repo;
    }

    public List<DukaanItem> getAllItems(){
        return repo.findAll();
    }

    public DukaanItem getItem(int id){
        if(repo.existsById(id)){
            return repo.getReferenceById(id);
        }else{
            return new DukaanItem();
        }
    }
    
    public String AddItem(DukaanItem item){
        repo.save(item);
        return "Item Saved with ID: "+ item.getId() + " Successfully";
    }

    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

}
