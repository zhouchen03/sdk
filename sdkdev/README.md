**********************
* sdk README *
**********************
1. Modules and dependencies in sdk
sdk debug build depends on spoofer
spoofer

2. publish the libraries
see publish_maven_android.gradle

3. In each module, you can find gradle task. Each of the task will make the build before upload/publish the library.
Tasks/publishing/publishMavenDebugAARPublicationToMavenLocal
Tasks/publishing/publishMavenDebugAARPublicationToMavenRepository
Tasks/publishing/publishMavenReleaseAARPublicationToMavenLocal
Tasks/publishing/publishMavenReleaseAARPublicationToMavenRepository

publishMaven<Type>ToMavenLocal: publish library in maven local
publishMaven<Type>ToMavenRepository: publish library to nexus

4. maven local

Users/<User_Name>/.m2/repository/com/example/sdk/client/android

5. maven nexus repositories, you can find your library after publish it in the following location:
url to find the libraries:
    http://localhost:8081/nexus
repositories:
    Public Repositories/com/example/sdk/client/android

url to publish the libraries:
    sdkNexusUrl                         = "http://localhost:8081/nexus/content/repositories/releases/"
    sdkNexusSnapshotsUrl                = "http://localhost:8081/nexus/content/repositories/snapshots/"
