package me.destro.android.dextoolkit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import me.destro.android.toolkit.Connectivity;

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
        assert Connectivity.isConnected(contextMock);

        when(networkInfo.isConnected()).thenReturn(false);
        assert !Connectivity.isConnected(contextMock);
    }
}
