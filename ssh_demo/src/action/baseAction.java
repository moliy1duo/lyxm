package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class baseAction {
	//ActionContext都是用来存放数据的
	//ActionContext是Action的上下文，Struts2自动在其中保存了一些在Action执行过程中所需的对象，比如session, parameters, locale等
	//Struts2会根据每个执行HTTP请求的线程来创建对应的ActionContext，即一个线程有一个唯一的ActionContext
	//使用者可以使用静态方法ActionContext.getContext()来获取当前线程的ActionContext，也正是由于这个原因，使用者不用去操心让Action是线程安全的。
	
	
	//ActionContext本身的数据结构是映射结构，即一个Map，用key来映射value。所以使用者完全可以像使用Map一样来使用它，或者直接使用Action.getContextMap()方法来对Map进行操作。
    protected ActionContext context = ActionContext.getContext();

    static ArrayList userNames = new ArrayList();
    static ArrayList qxs = new ArrayList();
    public baseAction(){
    }

    public HttpServletRequest getRequest(){
        return ServletActionContext.getRequest();
    }

    public HttpServletResponse getResponse(){
        return ServletActionContext.getResponse();
    }
    public HttpSession getSession(){
        return getRequest().getSession();
    }

    public ServletContext getServletContext(){
        return ServletActionContext.getServletContext();
    }

    public String getRealPath(String path){
        if(path == null||path.equals("")){
            path = "/";
        }
        return getServletContext().getRealPath(path);
    }
    public PrintWriter getWiter() throws IOException{
        HttpServletResponse response = this.getResponse();
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);
        response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
        return response.getWriter();
    }
    public void print(String info) throws IOException{
        HttpServletResponse response = this.getResponse();
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);
        response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(info);
        out.flush();
    }
}
