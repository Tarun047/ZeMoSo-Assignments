package Facade;

import java.util.Arrays;

public class LMSFacade {

    Credentials mCred;
    Courses mCourses;
    public LMSFacade(String id,String password)
    {
        mCred = new Credentials(id,password);
        mCourses = new Courses();
    }

    public void login()
    {
        if(!mCred.tryLogin())
            System.out.println(mCred.getId()+ " Couldn't log you in!\n");
        else
            System.out.println(mCred.getId()+ " Now you may access Courses\n");
    }

    public void logout()
    {
        mCred.logout();
    }

    public void getCourses()
    {
        if(mCred.isLogged())
            System.out.println(mCred.getId()+" "+Arrays.toString(mCourses.getCourses()));
        else
            System.out.println(mCred.getId()+" You must first login to access courses\n");
    }

}
