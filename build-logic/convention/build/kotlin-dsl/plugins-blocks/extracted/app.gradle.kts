
                                   
                                                                          

plugins {
                                     
    alias(libs.findPlugin("application").orElseThrow())
    alias(libs.findPlugin("kotlinAndroid").orElseThrow())
    alias(libs.findPlugin("ksp").orElseThrow())
}