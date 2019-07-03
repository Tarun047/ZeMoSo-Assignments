package JLocker;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

class Guard implements Vault {
    private File folder;
    Guard()
    {
        folder = new File(".locker");
        if(!folder.exists())
            folder.mkdir();
    }

    public void addFile(String filename)throws IOException
    {

       File f =  new File(".locker/"+filename);
       if(!f.exists())
           f.createNewFile();
       else
           System.out.println("File already present");
    }


    public void removeFile(String filename)
    {
        for(File f:folder.listFiles())
        {
            if(f.getName().equals(filename))
            {
                if(f.delete()) {
                    System.out.println("Delete Succeeded");
                    return;
                }
                else
                    System.out.println("Delete Failed!");
            }
        }
        System.out.println("Can't find a file with the name");
    }

    @Override
    public String[] listFiles() {
        return Stream.of(Objects.requireNonNull(folder.listFiles())).map((File f)->f!=null?f.getName():"No Files buddy!").toArray(String[]::new);

    }

    public void lock()
    {

    }

    public void unlock()
    {

    }

}
