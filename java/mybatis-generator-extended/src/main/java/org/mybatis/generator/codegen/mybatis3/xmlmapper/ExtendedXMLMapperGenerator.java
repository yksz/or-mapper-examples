package org.mybatis.generator.codegen.mybatis3.xmlmapper;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.XmlConstants;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.SimpleSelectAllElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.SimpleSelectByPrimaryKeyElementGenerator;

public class ExtendedXMLMapperGenerator extends XMLMapperGenerator {

    public ExtendedXMLMapperGenerator() {
        super();
    }

    @Override
    protected XmlElement getSqlMapElement() {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(getString("Progress.12", table.toString())); //$NON-NLS-1$
        XmlElement answer = new XmlElement("mapper"); //$NON-NLS-1$
        String namespace = introspectedTable.getMyBatis3SqlMapNamespace();
        answer.addAttribute(new Attribute("namespace", namespace)); //$NON-NLS-1$

        context.getCommentGenerator().addRootComment(answer);

        addResultMapWithoutBLOBsElement(answer);
        addSelectAllElement(answer);
        addSelectByPrimaryKeyElement(answer);
        addInsertSelectiveElement(answer);
        addUpdateByPrimaryKeySelectiveElement(answer);
        addDeleteByPrimaryKeyElement(answer);

        return answer;
    }

    @Override
    protected void addSelectByPrimaryKeyElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateSelectByPrimaryKey()) {
            AbstractXmlElementGenerator elementGenerator = new SimpleSelectByPrimaryKeyElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addSelectAllElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new SimpleSelectAllElementGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    @Override
    public Document getDocument() {
        Document document = new Document(
                XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID,
                XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);
        document.setRootElement(getSqlMapElement());

        if (!context.getPlugins().sqlMapDocumentGenerated(document,
                introspectedTable)) {
            document = null;
        }

        return document;
    }

}
