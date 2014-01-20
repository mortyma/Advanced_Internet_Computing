package cloudscale;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.infosys.cloudscale.annotations.FileDependency.DependentFile;
import at.ac.tuwien.infosys.cloudscale.annotations.FileDependency.IFileDependencyProvider;

public class MyDependencyProvider implements IFileDependencyProvider 
{
    @Override
    public DependentFile[] getDependentFiles() 
    {
            List<DependentFile> dependentFiles = new ArrayList<DependentFile>();
            File fileFolder = new File("files");
            if(fileFolder.exists())
            {
                    for(File file : fileFolder.listFiles())
                    {
                            if(file.isFile())
                                    dependentFiles.add(new DependentFile(file.getPath()));
                    }
            }
            
            return dependentFiles.toArray(new DependentFile[dependentFiles.size()]);
    }

}
