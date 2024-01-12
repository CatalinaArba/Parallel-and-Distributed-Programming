plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.mapsmessaging:Non_Block_Task_Scheduler:2.1.7")
    implementation("io.mapsmessaging:Non_Block_Task_Scheduler:2.1.7")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")


}

tasks.test {
    useJUnitPlatform()
}