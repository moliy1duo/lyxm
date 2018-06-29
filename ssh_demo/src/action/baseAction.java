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
	//ActionContext��������������ݵ�
	//ActionContext��Action�������ģ�Struts2�Զ������б�����һЩ��Actionִ�й���������Ķ��󣬱���session, parameters, locale��
	//Struts2�����ÿ��ִ��HTTP������߳���������Ӧ��ActionContext����һ���߳���һ��Ψһ��ActionContext
	//ʹ���߿���ʹ�þ�̬����ActionContext.getContext()����ȡ��ǰ�̵߳�ActionContext��Ҳ�����������ԭ��ʹ���߲���ȥ������Action���̰߳�ȫ�ġ�
	
	
	//ActionContext��������ݽṹ��ӳ��ṹ����һ��Map����key��ӳ��value������ʹ������ȫ������ʹ��Mapһ����ʹ����������ֱ��ʹ��Action.getContextMap()��������Map���в�����
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
