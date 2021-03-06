package engine;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener
{
    private static MouseListener instance;
    private double scrollX, scrollY;
    private double xPos, yPos, lastY, lastX;
    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;

    private MouseListener()
    {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    public static MouseListener get()
    {
        if (MouseListener.instance == null)
        {
            MouseListener.instance = new MouseListener();
        }

        return MouseListener.instance;
    }

    public static void MousePosCallback(long window, double xpos, double ypos)
    {
        get().lastX = get().xPos;
        get().lastY = get().yPos;
        get().xPos = xpos;
        get().yPos = ypos;
        get().isDragging = get().mouseButtonPressed[0] || get().mouseButtonPressed[1] || get().mouseButtonPressed[2];
    }

    public static void MouseButtonCallback(long window, int button, int action, int mods)
    {
        if (action == GLFW_PRESS)
        {
            if (button < get().mouseButtonPressed.length)
            {
                get().mouseButtonPressed[button] = true;
            }
        }
        else if (action == GLFW_RELEASE)
        {
            if (button < get().mouseButtonPressed.length)
            {
                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }
    }

    public static void MouseScrollCallback(long window, double xOffset, double yOffset)
    {
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    public static void EndFrame()
    {
        get().scrollX = 0;
        get().scrollY = 0;
        get().lastX = get().xPos;
        get().lastY = get().yPos;
    }

    public static float GetX()
    {
        return (float)get().xPos;
    }

    public static float GetY()
    {
        return (float)get().yPos;
    }

    public static float GetDx()
    {
        return (float)(get().lastX - get().xPos);
    }

    public static float GetDy()
    {
        return (float)(get().lastY - get().yPos);
    }

    public static float GetScrollX()
    {
        return (float)get().scrollX;
    }

    public static float GetScrollY()
    {
        return (float)get().scrollY;
    }

    public static boolean IsDragging()
    {
        return get().isDragging;
    }

    public static boolean MouseButtonDown(int button)
    {
        if (button < get().mouseButtonPressed.length)
        {
            return get().mouseButtonPressed[button];
        }
        else
        {
            return false;
        }
    }
}
