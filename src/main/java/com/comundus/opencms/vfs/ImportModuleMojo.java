package com.comundus.opencms.vfs;

import java.io.IOException;

import org.apache.maven.plugin.MojoExecutionException;
import org.opencms.main.CmsException;
import org.xml.sax.SAXException;

import com.comundus.opencms.VfsImportModule;

/**
 * A Maven2 plugin Goal to import a module from module ZIP - file
 *
 * @goal import-module
 */
public class ImportModuleMojo extends AbstractVfsMojo {
    /**
     * The _opencmsshell class to instantiate within our custom ClassLoader.
     */
    private static final String SHELLCLASS = "com.comundus.opencms.VfsImportModule";

    private static final String ERROR_MESSAGE = "Failed to instantiate (abstract!)" + ImportModuleMojo.SHELLCLASS;

    /**
     * Module file name
     * 
     * @parameter expression="${importFileName}"
     */
    private String importFileName;

    /**
     * Extracts a module from the targeted OpenCms.
     *
     * @throws MojoExecutionException
     *             in case anything goes wrong
     */
    public final void execute() throws MojoExecutionException {

        try {
            final VfsImportModule module = new VfsImportModule();
            module.execute(getWebappDirectory(), getAdminPassword(), this.importFileName);
        } catch (NoClassDefFoundError e) {
            throw new MojoExecutionException("Failed to load " + ImportModuleMojo.SHELLCLASS, e);
        } catch (IOException e) {
            throw new MojoExecutionException(ERROR_MESSAGE, e);
        } catch (CmsException e) {
            throw new MojoExecutionException(ERROR_MESSAGE, e);
        } catch (SAXException e) {
            throw new MojoExecutionException(ERROR_MESSAGE, e);
        }
    }
}
