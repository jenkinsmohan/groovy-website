layout 'layouts/main.groovy', true,
        pageTitle: 'The Groovy programming language - Download',
        mainContent: contents {
            div(id: 'content', class: 'page-1') {
                div(class: 'row') {
                    div(class: 'row-fluid') {
                        div(class: 'col-lg-3') {
                            ul(class: 'nav-sidebar') {
                                li(class: 'active') {
                                    a(href: 'download.html') { strong('Download Groovy') }
                                }
                                li {
                                    a(href: '#distro', class: 'anchor-link', 'Distributions')
                                }
                                li {
                                    a(href: '#sdkman', class: 'anchor-link', 'Through SDKMAN!')
                                }
                                li {
                                    a(href: '#buildtools', class: 'anchor-link', 'From your build tools')
                                }
                                li {
                                    a(href: '#otherways', class: 'anchor-link', 'Other ways to get Groovy')
                                }
                                li {
                                    a(href: '#requirements', class: 'anchor-link', 'System requirements')
                                }
                                li {
                                    a(href: 'versioning.html', 'Groovy version scheme')
                                }
                                li {
                                    a(href: 'indy.html', 'Invoke dynamic support')
                                }
                                li {
                                    a(href: 'releases.html', 'Release notes')
                                }
                            }
                        }

                        div(class: 'col-lg-8 col-lg-pull-0') {
                            include template: 'includes/contribute-button.groovy'
                            h1 {
                                i(class: 'fa fa-cloud-download') {}
                                yield ' Download'
                            }
                            def linkVersionToDownload = distributions.collect { it.packages }.flatten().find { it.stable }.version
                            button(id: 'big-download-button', type: 'button', class: 'btn btn-default',
                                    title: "Download Groovy ${linkVersionToDownload}",
                                    onclick: "window.location.href=\"https://dl.bintray.com/groovy/maven/apache-groovy-sdk-${linkVersionToDownload}.zip\"") {
                                i(class: 'fa fa-download') {}
                                yield ' Download'
                            }
                            article {
                                p {
                                    yield 'In this download area, you will be able to download the '
                                    a(href: '#distro', 'distribution')
                                    yield ' (binary and source), the Windows installer (for some of the versions) and the documentation for Groovy.'
                                }
                                p {
                                    yield 'All downloads (except the source download) are hosted in '
                                    a(href: 'http://bintray.com/groovy/', 'Bintray\'s Groovy repository')
                                    yield '. Registering on Bintray allows you to rate, review, and register for new version notifications.'
                                    a(href: 'https://dl.bintray.com/groovy/maven/', '[direct link to the downloads]')
                                }
                                p {
                                    yield 'For a quick and effortless start on Mac OSX, Linux or Cygwin, you can use '
                                    a(href: 'http://sdkman.io/', 'SDKMAN! (The Software Development Kit Manager)')
                                    yield ' to download and configure any Groovy version of your choice. Basic '
                                    a(href: '#sdkman', 'instructions')
                                    yield ' can be found below. '
                                    br()
                                    yield 'Windows users can use '
                                    a(href: 'https://github.com/flofreud/posh-gvm', 'Posh-GVM')
                                    yield ' (POwerSHell Groovy enVironment Manager), a PowerShell clone of the GVM CLI.'
                                }
                            }
                            hr(class: 'divider')

                            a(name: 'distro') {}
                            article {
                                h1 'Distributions'
                                p 'You can download a binary, a source or a documentation bundle, as well as a bundle of all three.'

                                distributions.each { dist ->
                                    h2 {
                                        i(class: 'fa fa-star') {}
                                        yield " ${dist.name}"
                                    }
                                    if (dist.description) {
                                        p {
                                            dist.description.rehydrate(this, this, this)()
                                        }
                                    }
                                    dist.packages.each { pkg ->
                                        h3 "${pkg.version} distributions"
                                        table(width: '100%', class: 'download-table') {
                                            tr {
                                                td {
                                                    a(href: "https://dl.bintray.com/groovy/maven/apache-groovy-binary-${pkg.version}.zip") {
                                                        i(class: 'fa fa-gears fa-4x') {}
                                                        br()
                                                        yield 'binary'
                                                    }
                                                }
                                                td {
                                                    a(href: "http://www.apache.org/dyn/closer.cgi/groovy/${pkg.version}/sources/apache-groovy-src-${pkg.version}.zip") {
                                                        i(class: 'fa fa-code fa-4x') {}
                                                        br()
                                                        yield ' source'
                                                    }
                                                }
                                                td {
                                                    a(href: "https://dl.bintray.com/groovy/maven/apache-groovy-docs-${pkg.version}.zip") {
                                                        i(class: 'fa fa-file-text fa-4x') {}
                                                        br()
                                                        yield ' documentation'
                                                    }
                                                }
                                                td {
                                                    a(href: "https://dl.bintray.com/groovy/maven/apache-groovy-sdk-${pkg.version}.zip") {
                                                        i(class: 'fa fa-file-zip-o fa-4x') {}
                                                        br()
                                                        yield ' SDK bundle'
                                                    }
                                                }
                                                if (pkg.windowsInstaller) {
                                                    td {
                                                        a(href: pkg.windowsInstaller) {
                                                            i(class: 'fa fa-windows fa-4x') {}
                                                            br()
                                                            yield ' Windows installer'
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        p {
                                            yield 'Please consult the '
                                            a(href: "changelogs/changelog-${pkg.version}.html", ' change log')
                                            yield ' for details. Please read the '
                                            a(href: "indy.html", 'invoke dynamic support information')
                                            yield ' if you like to use Groovy on JDK 7+.'
                                        }
                                    }
                                }
                                article {
                                    h3 'Changelog'

                                    p {
                                        yield 'You can also read the changelogs for '
                                        a(href: "changelogs.html", 'older versions')
                                        yield '.'
                                    }
                                }
                            }

                            hr(class: 'divider')

                            a(name: 'sdkman') {}
                            article {
                                h1 'SDKMAN! (The Software Development Kit Manager)'
                                p {
                                    yield 'This tool makes installing Groovy on any Bash platform (Mac OSX, Linux, Cygwin, Solaris or FreeBSD) very easy.'
                                    br()
                                    yield 'Simply open a new terminal and enter:'
                                }
                                pre { code '$ curl -s get.sdkman.io | bash' }
                                p {
                                    yield 'Follow the instructions on-screen to complete installation.'
                                    br()
                                    yield 'Open a new terminal or type the command:'
                                }
                                pre { code '$ source "$HOME/.sdkman/bin/sdkman-init.sh"' }
                                p 'Then install the latest stable Groovy:'
                                pre { code '$ sdk install groovy' }
                                p 'After installation is complete and you\'ve made it your default version, test it with:'
                                pre { code '$ groovy -version' }
                                p 'That\'s all there is to it!'
                            }
                            hr(class: 'divider')

                            a(name: 'buildtools') {}
                            article {
                                h1 'From your build tools'
                                p 'If you wish to add Groovy as a dependency in your projects, you can refer to the Groovy JARs in the dependency section of your project build file descriptor:'
                                table(class: 'table') {
                                    thead {
                                        tr {
                                            th 'Gradle'
                                            th 'Maven'
                                            th 'Explanation'
                                        }
                                    }
                                    tbody {
                                        tr {
                                            td {
                                                code 'org.codehaus.groovy:groovy:x.y.z'
                                            }
                                            td {
                                                code '&lt;groupId&gt;org.codehaus.groovy&lt;/groupId&gt;'
                                                br()
                                                code '&lt;artifactId&gt;groovy&lt;/artifactId&gt;'
                                                br()
                                                code '&lt;version&gt;x.y.z&lt;/version&gt;'
                                            }
                                            td 'Just the core of Groovy without the modules (see below). Also includes jarjar\'ed versions of Antlr, ASM, and Commons-CLI.'
                                        }
                                        tr {
                                            td {
                                                code 'org.codehaus.groovy:groovy-$module:x.y.z'
                                            }
                                            td {
                                                code '&lt;groupId&gt;org.codehaus.groovy&lt;/groupId&gt;'
                                                br()
                                                code '&lt;artifactId&gt;groovy-$module&lt;/artifactId&gt;'
                                                br()
                                                code '&lt;version&gt;x.y.z&lt;/version&gt;'
                                            }
                                            td {
                                                code '"$module"'
                                                yield ' stands for the different optional groovy modules "ant", "bsf", "console", "docgenerator", "groovydoc", "groovysh", "jmx", "json", "jsr223", "nio", "servlet", "sql", "swing", "test", "templates", "testng" and "xml".'
                                                br()
                                                yield 'Example: '
                                                code '&lt;artifactId&gt;groovy-sql&lt;/artifactId&gt;'
                                            }
                                        }
                                        tr {
                                            td {
                                                code 'org.codehaus.groovy:groovy-all:x.y.z'
                                            }
                                            td {
                                                code '&lt;groupId&gt;org.codehaus.groovy&lt;/groupId&gt;'
                                                br()
                                                code '&lt;artifactId&gt;groovy-all&lt;/artifactId&gt;'
                                                br()
                                                code '&lt;version&gt;x.y.z&lt;/version&gt;'
                                            }
                                            td 'The core plus all the modules. Also includes <em>jarjar\'ed</em> versions of Antlr, ASM, Commons-CLI.\n' +
                                                    'Optional dependencies are marked as optional.\n' +
                                                    'You may need to include some of the optional dependencies to use some features of Groovy, e.g. AntBuilder, GroovyMBeans...'
                                        }
                                    }
                                }
                                h3 'Maven repositories'
                                p "Groovy releases are downloadable from ${$a(href:'http://repo1.maven.org/maven2/org/codehaus/groovy/','Maven Central')} or ${$a(href:'http://jcenter.bintray.com/org/codehaus/groovy/','JCenter')}."
                                p "Groovy snapshots are downloadable from ${$a(href:'https://oss.jfrog.org/oss-snapshot-local/org/codehaus/groovy','JFrog OpenSource Snapshots repository')}."
                            }
                            hr(class: 'divider')

                            a(name: 'otherways') {}
                            article {
                                h1 'Other ways to get Groovy'
                                p {
                                    yield 'If you\'re on MacOS and have '
                                    a(href: 'http://brew.sh/', 'Homebrew')
                                    yield ' installed, you can install Groovy with:'
                                    pre { code 'brew install groovy' }
                                    yield 'If you\'re on MacOS and have '
                                    a(href: 'http://www.macports.org/', 'MacPorts')
                                    yield ' installed, you can install Groovy with:'
                                    pre { code 'sudo port install groovy' }
                                    yield 'If you\'re using Docker, Groovy is available on '
                                    a(href: 'https://hub.docker.com/_/groovy/', 'Docker Hub')
                                    yield '.'
                                    br()
                                    yield 'If you prefer to live on the bleeding edge, you can also grab the '
                                    a(href: 'https://github.com/apache/groovy', 'source code from GitHub')
                                    yield '.'
                                    br()
                                    yield 'If you are an IDE user, you can just grab the latest '
                                    a(href: 'ides.html', 'IDE plugin')
                                    yield ' and follow the plugin installation instructions.'
                                }
                            }
                            a(name: 'requirements') {}
                            article {
                                h1 'System requirements'
                                p {
                                    table(class: 'table') {
                                        thead {
                                            tr {
                                                th 'Groovy'
                                                th 'JVM Required (non-indy)'
                                                th 'JVM Required (indy) *'
                                            }
                                        }
                                        tbody {
                                            tr {
                                                td { b '2.3 - current' }
                                                td '1.6+'
                                                td '1.7+'
                                            }
                                            tr {
                                                td { b '2.0 - 2.2' }
                                                td '1.5+'
                                                td '1.7+'
                                            }
                                            tr {
                                                td { b '1.6 - 1.8' }
                                                td '1.5+'
                                                td 'N/A'
                                            }
                                            tr {
                                                td { b '1.0 - 1.5' }
                                                td '1.4+'
                                                td 'N/A'
                                            }
                                        }
                                    }
                                    yield '* If you plan to use invoke dynamic support, please read the '
                                    a(href: "indy.html", 'support information')
                                    yield '.'
                                }
                            }
                        }
                    }
                }
            }
        }
