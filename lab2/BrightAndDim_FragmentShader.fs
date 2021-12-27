#version 330 core

out vec4 FragColor;
uniform float time;
uniform vec3 rgb;
//uniform mat4 modelView;
//uniform mat4 modelProj;
in vec4 vertexColor; // the input variable from the vertex shader (same name and same type)  

void main()
{
    //FragColor = vec4(rgb, 1.0f);
    float hue = ((cos(time*2)+3)/4);
        FragColor = vec4(rgb*hue, 1.0);//vec4(rgb.x*hue, rgb.y*hue, rgb.z*hue, 1.0);
    //FragColor = vertexColor;
} 