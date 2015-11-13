package suhael.service.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class NavigationController implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value="#{param.pageId}")
    private String pageId;

    public String moveToPage1(){
        return "about";
    }

    public String moveToPage2(){
        return "contact";
    }

    public String moveToHomePage(){
        return "home";
    }

    public String processPage1(){
        return "page";
    }

    public String processPage2(){
        return "page";
    }

    public String showPage(){
        if(pageId == null){
            return "home";
        }
        if(pageId.equals("1")){
            return "about";
        }else if(pageId.equals("2")){
            return "contact";
        }else{
            return "home";
        }
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
}