package tool

import groovy.sql.Sql

class SqlExecutor {

    static def getResource(name) {
        new File(ClassLoader.getSystemResource(name).toURI())
    }

    static main(args) {
        def db = new Properties()
        getResource('db.properties').withInputStream { db.load(it) }
        def sql = Sql.newInstance(db.url, db.user, db.password, db.driver)
        getResource('sql').eachFile {
            if (it.name.tokenize('.').last() == 'sql') {
                println it.name
                sql.execute(it.text)
            }
        }
    }

}
