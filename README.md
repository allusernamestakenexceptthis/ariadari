
# Portfolio website with CMS

Portfolio website being developed in Java Spring Boot Framework and Nuxt 3 with SSR and CMS features.
Started this site to learn Java spring boot.

ポートフォリオのウェブサイトはJava Spring BootフレームワークとNuxtでCMSを構築されています。Springの学習を進めながら開発を行っています。

## Installation

create .env.properties file and make sure it is inaccessible to the public
include database credentials in this format:

`
DB_NAME=YOURDBNAME
DB_USERNAME=YOURUSERNAME
DB_PASSWORD=YOURPASS
`

then change spring.config.import in applicaiton.properties to reference that file

go to vuefront
`
npm isntall
npm build
`

back to root path run

`
gradlew build
`

or run

`
gradlew webapp:bootRun -Pdev
`

build for production

`
./gradlew build -Pprod -x test
`