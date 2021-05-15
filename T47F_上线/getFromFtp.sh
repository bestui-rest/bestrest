##TEP
TEPPATH=/home/amlweb/T47F/temp ;
rm  -fr $TEPPATH ;
mkdir $TEPPATH;
cd $TEPPATH;
##162.157.12.22
##BTJ120037,267367
REMPATH=/home/dataexchange/got_data_inner ;
USERAPWD=BTJ120037,267367 ;
REIP=162.157.12.22   ;
lftp -u $USERAPWD sftp://$REIP:22<<EOF
cd $REMPATH
mget *.txt
bye
EOF
