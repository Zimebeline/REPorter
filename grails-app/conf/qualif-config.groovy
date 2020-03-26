
/**
 * Configuration de la dataSource principale
 */
dataSource {
    dbCreate = "update"
    url = "jdbc:postgresql://localhost/rep"
    username = "rep"
    password = "rep"
	
	url = "jdbc:postgresql://pgsql1-qualif.province-sud.qualif:5432/rep?ApplicationName=rep"
	username = "rep"
	password = "rep"
    driverClassName = "org.postgresql.Driver"
    dialect = "common.orm.dialect.CommonPostgres93Dialect"
    pooled = true
    logSql = false
    properties {
        validationQuery="select 1"
        testOnBorrow = true
    }
}

/**
 * Configuration Hibernate
 */
hibernate {
    naming_strategy = common.orm.PsudNamingStrategy
    cache.use_second_level_cache = false
    cache.use_query_cache = false
    show_sql = false
    format_sql = false
    use_sql_comments = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}

/**
 * Configuration de Grails
 */
grails {
 
    serverURL = "http://localhost:8080/rep"
}

/**
 * Configuration du Common-plugin
 */
common {

    barreSip = 'dev'

    environnement = common.security.Environnement.DEVELOPMENT

    title {
        part1 = "REP - "
        part2 = "Description de l'application"
    }

    security {

        authentication = common.security.AuthMethod.FORM
        habilitation = common.security.Habilitation.CONFIG

        listeUser.'user' = [
            email: "user.rep@your-domain.nc",
            securityType : "GUD",
            REP_USER : []
        ]

        listeUser.'admin' = [
            email: "admin.rep@your-domain.nc",
            securityType : "GUD",
            REP_USER : [],
            REP_ADMIN: []
        ]
    }
}

/**
 * Configuration de log4j
 */
log4j = {
    root {
        debug "grails.app"
        debug 'org'
        debug "grails.app.controllers"
    }
}
