package tool

import org.mybatis.generator.api.MyBatisGenerator
import org.mybatis.generator.config.xml.ConfigurationParser
import org.mybatis.generator.internal.DefaultShellCallback
import org.mybatis.generator.internal.ExtendedDefaultShellCallback

class CodeGenerator {

    static def getResource(name) {
        new File(ClassLoader.getSystemResource(name).toURI())
    }

    static main(args) {
        def warnings = []
        def overwrite = true
        def configFile = getResource('generatorConfig.xml')
        def cp = new ConfigurationParser(warnings)
        def config = cp.parseConfiguration(configFile)
        def callback = new ExtendedDefaultShellCallback(overwrite)
        def myBatisGenerator = new MyBatisGenerator(config, callback, warnings)
        myBatisGenerator.generate(null)
    }

}
