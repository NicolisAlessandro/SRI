# -*- coding: utf-8 -*-
__author__ = "deMartini Francesco"
__version__ = "0101 2021/11/04"

import os
from pathlib import Path

head, tail = os.path.split(__file__)
os.chdir(head)
percorso = os.getcwd()

# CONSTANT
srcGitDirectory = "https://github.com/NicolisAlessandro/SRI/tree/master"
readmePath = "/doc/README.md"
# README
intestazioneMD = """# SRI

"""
lastMD = """## Last

| PROJECT | README |
| :--- | ---: |
"""

projectsMD = """
## Projects

| PROJECT | README |
| :--- | ---: |
"""


def lastProjectString(dirUpdated):
    string = "| null | null |\n"
    if dirUpdated != "":
        top, end = os.path.split(dirUpdated)
        src = Path(dirUpdated).resolve().parts
        srcName = src[len(src) - 2]
        string = "| " + \
                 "[" + end + "]" + \
                 "(" + srcGitDirectory + "/" + srcName + "/" + end + "/bin)" + \
                 " | " + \
                 "[ReadMe]" + \
                 "(" + srcGitDirectory + "/" + srcName + "/" + end + readmePath + ")" + \
                 " |" + \
                 "\n"
    return string


def projectsString(srcDirectory, dirProjectName):
    string = ""
    for directory in os.listdir(srcDirectory):
        srcName = Path(srcDirectory).resolve().name

        if directory.startswith(dirProjectName):
            string += "| " + \
                      "[" + directory + "]" + \
                      "(" + srcGitDirectory + "/" + srcName + "/" + directory + "/bin)" + \
                      " | " + \
                      "[ReadMe]" + \
                      "(" + srcGitDirectory + "/" + srcName + "/" + directory + readmePath + ")" + \
                      " |" + \
                      "\n"
        else:
            continue
    return string


def writeReadme(lastString, projectString):
    try:
        fileReadme = open(head + "//README.md", "w")

        fileReadme.write(
            intestazioneMD +
            lastMD +
            lastString +
            projectsMD +
            projectString
        )
        fileReadme.close()
    except IOError:
        return False
    return True


def updateMD(srcDirectory, dirProjectName, dirUpdated):
    return writeReadme(lastProjectString(dirUpdated), projectsString(srcDirectory, dirProjectName))


boold = True
if __name__ == "__main__":
    if boold:
        print("Start")

        updateMD(head + "/src/", "nicolis_A_", "C:/Users/Utente/Documents/Scuola/INFO/GitHub/SRI/src/nicolis_A_http_cl_01")

    if boold:
        print("End")
