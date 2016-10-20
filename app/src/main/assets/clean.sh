#!/system/bin/sh
echo ---------------------------------------
echo ---------- Made By : Mr.W0lf ----------
echo ---- Thanks @Chainfire for SuperSU ----
echo ---------------------------------------
mount -o rw,remount /system
am kill com.kingroot.RushRoot
pm uninstall com.kingroot.RushRoot
am kill com.kingroot.kinguser
pm uninstall com.kingroot.kinguser
rm /system/app/Kinguser.apk >/dev/null
rm -r /system/app/Kinguser >/dev/null
am kill com.kingroot.master
pm uninstall com.kingroot.master >/dev/null
cat /sdcard/mrw/busybox > /system/bin/busybox
chown 0.1000 /system/bin/busybox
chmod 0755 /system/bin/busybox
busybox chattr -ia /system/xbin/ku.sud
rm /system/xbin/ku.sud
busybox chattr -ia /system/xbin/kugote >/dev/null 2>&1
rm /system/xbin/kugote >/dev/null 2>&1
busybox chattr -ia /system/xbin/su
rm /system/xbin/su
busybox chattr -ia /system/xbin/supolicy
rm /system/xbin/supolicy
busybox chattr -ia /system/xbin/pidof >/dev/null 2>&1
rm /system/xbin/pidof >/dev/null 2>&1
cat /sdcard/mrw/su > /system/xbin/su
cat /sdcard/mrw/su > /system/xbin/daemonsu
cat /sdcard/mrw/su > /system/xbin/sugote
cat /system/bin/sh > /system/xbin/sugote-mksh
chown 0.0 /system/xbin/su
chmod 6755 /system/xbin/su
chown 0.0 /system/xbin/sugote
chmod 0755 /system/xbin/sugote
chown 0.0 /system/xbin/sugote-mksh
chmod 0755 /system/xbin/sugote-mksh
chown 0.0 /system/xbin/daemonsu
chmod 0755 /system/xbin/daemonsu
daemonsu -d
rm -r /data/app/com.kingroot.RushRoot-1 >/dev/null 2>&1
rm -r /data/data/com.kingroot.RushRoot
rm -r /data/data-lib/com.kingroot.RushRoot
rm -r /data/app/com.kingroot.kinguser-1 >/dev/null 2>&1
rm -r /data/data/com.kingroot.kinguser
rm -r /data/data-lib/com.kingroot.kinguser
rm -r /data/app/com.kingroot.master-1 >/dev/null 2>&1
rm -r /data/data/com.kingroot.master
rm -r /data/data-lib/king >/dev/null 2>&1
busybox chattr -ia /system/bin/.usr/.ku
rm /system/bin/.usr/.ku
busybox chattr -ia /system/bin/rt.sh
rm /system/bin/rt.sh
busybox chattr -ia /system/bin/su
rm /system/bin/su
busybox chattr -ia /system/bin/ddexe-ku.bak >/dev/null 2>&1
rm /system/bin/ddexe-ku.bak >/dev/null 2>&1
busybox chattr -ia /system/bin/ddexe
rm /system/bin/ddexe
busybox chattr -ia /system/bin/ddexe_real >/dev/null 2>&1
rm /system/bin/ddexe_real >/dev/null 2>&1
busybox chattr -ia /system/bin/install-recovery.sh
rm /system/bin/install-recovery.sh
busybox chattr -ia /system/bin/install-recovery.sh-ku.bak
rm /system/bin/install-recovery.sh-ku.bak
pm uninstall eu.chainfire.supersu >/dev/null 2>&1
pm install /sdcard/mrw/superuser.apk
busybox chattr -ia /system/usr/iku/isu
rm -r /system/usr/iku
rm -r /dev/reportroot
busybox chattr -ia /system/etc/install-recovery.sh
rm /system/etc/install-recovery.sh
busybox chattr -ia /system/etc/install_recovery.sh
rm -r /system/app/Kinguser
rm -r /data/data-lib/king
rm -r /sdcard/Kingroot
rm /sdcard/kr-stock-conf >/dev/null 2>&1
am start -a android.intent.action.MAIN -n eu.chainfire.supersu/.MainActivity >/dev/null
sleep 2