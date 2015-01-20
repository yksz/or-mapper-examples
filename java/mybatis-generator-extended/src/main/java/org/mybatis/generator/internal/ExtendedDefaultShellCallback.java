package org.mybatis.generator.internal;

import org.mybatis.generator.exception.ShellException;

public class ExtendedDefaultShellCallback extends DefaultShellCallback {

    public ExtendedDefaultShellCallback(boolean overwrite) {
        super(overwrite);
    }

    @Override
    public boolean isMergeSupported() {
        return true;
    }

    @Override
    public String mergeJavaFile(String newFileSource,
            String existingFileFullPath, String[] javadocTags, String fileEncoding)
            throws ShellException {
        return newFileSource;
    }

}
