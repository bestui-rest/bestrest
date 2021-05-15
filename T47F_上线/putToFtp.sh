##TEP
TMPPATH=/home/amlweb/T47F/tempput;
cd $TMPPATH;
##162.157.12.22
##BTJ120037,267367
REMPATH=/home/dataexchange/got_data_outer ;
USERAPWD=BTJ120037,267367 ;
REIP=162.157.12.22   ;
lftp -u $USERAPWD sftp://$REIP:22<<EOF
cd $REMPATH
mput *.txt
bye
EOF
