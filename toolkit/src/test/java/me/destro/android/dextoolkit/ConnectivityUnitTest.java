package me.destro.android.dextoolkit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import me.destro.android.toolkit.Connectivity;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConnectivityUnitTest {
    @Mock
    Connectivity connectivity = mock(Connectivity.class);

    @Mock
    ConnectivityManager connectivityManager = mock(ConnectivityManager.class);

    @Mock
    Context contextMock = mock(Context.class);

    @Mock
    NetworkInfo networkInfo = mock(NetworkInfo.class);

    @Before
    public void setup() {
        when(contextMock.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager);
        when(connectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);
    }

    @Test
    public void testIsConnected() {
        when(networkInfo.isConnected()).thenReturn(true);
        assertTrue(Connectivity.isConnected(contextMock));

        when(networkInfo.isConnected()).thenReturn(false);
        assertFalse(Connectivity.isConnected(contextMock));
    }

    @Test
    public void testIsConnectedWifi() {
        when(networkInfo.getType()).thenReturn(ConnectivityManager.TYPE_WIFI);
        when(networkInfo.isConnected()).thenReturn(true);
        assertTrue(Connectivity.isConnectedWifi(contextMock));
    }

    @Test
    public void testIsConnectedMobile() {
        when(networkInfo.getType()).thenReturn(ConnectivityManager.TYPE_MOBILE);
        when(networkInfo.isConnected()).thenReturn(true);
        assertTrue(Connectivity.isConnectedMobile(contextMock));
    }

    @Test
    public void testIsConnectedFast() {
        when(networkInfo.getType()).thenReturn(ConnectivityManager.TYPE_WIFI);
        when(networkInfo.isConnected()).thenReturn(true);
        assertTrue(Connectivity.isConnectedFast(contextMock));

        when(networkInfo.isConnected()).thenReturn(false);
        assertFalse(Connectivity.isConnectedFast(contextMock));

        when(networkInfo.getType()).thenReturn(ConnectivityManager.TYPE_MOBILE);
        when(networkInfo.isConnected()).thenReturn(true);

        when(networkInfo.getSubtype()).thenReturn(TelephonyManager.NETWORK_TYPE_1xRTT);
        assertFalse(Connectivity.isConnectedFast(contextMock));

        when(networkInfo.getSubtype()).thenReturn(TelephonyManager.NETWORK_TYPE_LTE);
        assertTrue(Connectivity.isConnectedFast(contextMock));
    }
}
