# cw-temple
>java main.TXTmain -n 10000 2>&- |grep multiplier | awk -F":" '{total +=  $NF; count++} END { print total/count}'
1.22822
>java main.TXTmain -n 20000 2>&- |grep multiplier | awk -F":" '{total +=  $NF; count++} END { print total/count}'
1.22896
