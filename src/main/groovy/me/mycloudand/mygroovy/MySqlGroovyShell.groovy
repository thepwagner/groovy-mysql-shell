package me.mycloudand.mygroovy

import groovy.ui.Console
import groovy.sql.Sql

class MySqlGroovyShell {
    static void main(String... args) {
        CliBuilder cli = new CliBuilder(usage:'ls')
        cli.with {
            h longOpt: 'host', args: 1, required: false, 'Host'
            u longOpt: 'user', args: 1, required: false, 'Username'
            p longOpt: 'password', args: 1, required: false, 'Password'
            d longOpt: 'db', args: 1, required: false, 'Database'
        }

        def opts = cli.parse(args)
        def host = opts.h ?: 'localhost'
        def dbName = opts.d ?: 'mysql'
        def user = opts.u ?: 'root'
        def pass = opts.p ?: ''

        def binding = new Binding()
        binding['Sql'] = Sql.class

        def db = Sql.newInstance("jdbc:mysql://${host}:3306/${dbName}", user, pass, 'com.mysql.jdbc.Driver')
        binding['db'] = db
        Console console = new Console(binding)
        console.run()
    }
}
