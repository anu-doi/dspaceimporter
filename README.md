# dspaceimporter
Uploads to records to dspace via the swordv2.  This is current configured to find and upload records from Springer Nature and PLOS.

## Springer Nature
Example to upload articles from Springer Nature:

    java -jar uploader.jar springer -u -c 1885/26 -s 2018-11-15 -e 2018-11-22

## PLOS
Example to upload articles from PLOS

    java -jar uploader.jar plos -u -c 1885/26 -s 2018-11-15 -e 2018-11-22

