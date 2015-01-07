import groovy.sql.Sql

def dbconfig = 'src/main/resources/db.properties'

def db = new Properties()
new File(dbconfig).withInputStream { db.load(it) }

def sql = Sql.newInstance(db.url, db.user, db.password, db.driver)
new File('sql').eachFile {
    if (it.name.tokenize('.').last() == 'sql') {
        println it.name
        sql.execute(it.text)
    }
}
