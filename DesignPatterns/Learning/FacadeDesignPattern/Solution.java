import Facade.*;
class Solution
{
    public static void main(String[] args)
    {
        LMSFacade session1 = new LMSFacade("tarun@zemosolabs.com","1234");
        LMSFacade session2 = new LMSFacade("nikhil@zemosolabs.com","4567");
        LMSFacade session3 = new LMSFacade("someRandomId","someInvalidPass");

        session1.login();
        session1.getCourses();

        session2.getCourses();
        session1.logout();

        session2.login();
        session2.getCourses();

        session3.login();
        session3.getCourses();
    }
}