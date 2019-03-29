package me.destro.android.toolkit.permissions;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


public final class Permissions {
    private Permissions() {}

    public static boolean hasPermissionInManifest(Context context, String permissionName) throws PackageManager.NameNotFoundException {
        final String packageName = context.getPackageName();

        final PackageInfo packageInfo = context.getPackageManager()
                .getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);

        final String[] declaredPermissions = packageInfo.requestedPermissions;

        if (declaredPermissions != null && declaredPermissions.length > 0) {


            for (String p : declaredPermissions) {
                if (p.equals(permissionName)) {
                    return true;
                }
            }
        }

        return false;
    }
}
