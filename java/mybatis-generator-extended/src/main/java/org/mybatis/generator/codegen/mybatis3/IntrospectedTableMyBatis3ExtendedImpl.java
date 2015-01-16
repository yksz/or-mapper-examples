package org.mybatis.generator.codegen.mybatis3;

import java.util.List;

import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.ExtendedJavaMapperGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.SimpleAnnotatedClientGenerator;
import org.mybatis.generator.codegen.mybatis3.model.SimpleModelGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.ExtendedXMLMapperGenerator;
import org.mybatis.generator.internal.ObjectFactory;

public class IntrospectedTableMyBatis3ExtendedImpl extends IntrospectedTableMyBatis3Impl {

    public IntrospectedTableMyBatis3ExtendedImpl() {
        super();
    }

    @Override
    protected void calculateXmlMapperGenerator(AbstractJavaClientGenerator javaClientGenerator,
            List<String> warnings, ProgressCallback progressCallback) {
        if (javaClientGenerator == null) {
            if (context.getSqlMapGeneratorConfiguration() != null) {
                xmlMapperGenerator = new ExtendedXMLMapperGenerator();
            }
        } else {
            xmlMapperGenerator = javaClientGenerator.getMatchedXMLGenerator();
        }
        initializeAbstractGenerator(xmlMapperGenerator, warnings, progressCallback);
    }

    @Override
    protected AbstractJavaClientGenerator createJavaClientGenerator() {
        if (context.getJavaClientGeneratorConfiguration() == null) {
            return null;
        }

        String type = context.getJavaClientGeneratorConfiguration().getConfigurationType();
        AbstractJavaClientGenerator javaGenerator;
        if ("XMLMAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
            javaGenerator = new ExtendedJavaMapperGenerator();
        } else if ("ANNOTATEDMAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
            javaGenerator = new SimpleAnnotatedClientGenerator();
        } else if ("MAPPER".equalsIgnoreCase(type)) { //$NON-NLS-1$
            javaGenerator = new ExtendedJavaMapperGenerator();
        } else {
            javaGenerator = (AbstractJavaClientGenerator) ObjectFactory.createInternalObject(type);
        }
        return javaGenerator;
    }

    @Override
    protected void calculateJavaModelGenerators(List<String> warnings,
            ProgressCallback progressCallback) {
        AbstractJavaGenerator javaGenerator = new SimpleModelGenerator();
        initializeAbstractGenerator(javaGenerator, warnings, progressCallback);
        javaModelGenerators.add(javaGenerator);
    }

}
