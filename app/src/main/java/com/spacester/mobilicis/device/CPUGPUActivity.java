package com.spacester.mobilicis.device;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.EGL14;
import android.opengl.GLES20;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.spacester.mobilicis.R;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

public class CPUGPUActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpugpuactivity);

        findViewById(R.id.back).setOnClickListener(view -> onBackPressed());

        String cpuInfo = "Unknown";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String[] archs = Build.SUPPORTED_ABIS;
            if (archs != null && archs.length > 0) {
                cpuInfo = archs[0];
            }
        } else {
            cpuInfo = Build.CPU_ABI;
        }

        TextView cpu = findViewById(R.id.cpu);
        cpu.setText("CPU Info : "+ cpuInfo);

        EGL10 egl = (EGL10) EGLContext.getEGL();
        EGLDisplay display = egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        int[] version = new int[2];
        egl.eglInitialize(display, version);
        int[] configAttribs = {
                EGL10.EGL_RENDERABLE_TYPE, EGL14.EGL_OPENGL_ES2_BIT,
                EGL10.EGL_SURFACE_TYPE, EGL10.EGL_PBUFFER_BIT,
                EGL10.EGL_NONE
        };
        EGLConfig[] configs = new EGLConfig[1];
        int[] numConfigs = new int[1];
        egl.eglChooseConfig(display, configAttribs, configs, 1, numConfigs);
        if (numConfigs[0] == 0) {

            return;
        }
        EGLConfig config = configs[0];
        EGLContext context = egl.eglCreateContext(display, config, EGL10.EGL_NO_CONTEXT, null);
        EGLSurface surface = egl.eglCreatePbufferSurface(display, config, new int[]{EGL10.EGL_WIDTH, 1, EGL10.EGL_HEIGHT, 1, EGL10.EGL_NONE});
        egl.eglMakeCurrent(display, surface, surface, context);

        String renderer = GLES20.glGetString(GLES20.GL_RENDERER);
        String versionStr = GLES20.glGetString(GLES20.GL_VERSION);
        String vendor = GLES20.glGetString(GLES20.GL_VENDOR);

        TextView rende = findViewById(R.id.renderer);
        rende.setText("GPU Renderer: " + renderer);

        TextView ver = findViewById(R.id.version);
        ver.setText("GPU Version: " + versionStr);

        TextView ven = findViewById(R.id.vendor);
        ven.setText("GPU Vendor: " + vendor);

        egl.eglDestroySurface(display, surface);
        egl.eglDestroyContext(display, context);
        egl.eglTerminate(display);


    }
}