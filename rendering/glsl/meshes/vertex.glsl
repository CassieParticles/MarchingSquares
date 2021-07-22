#version 450

layout(location=0) in vec2 position;

uniform vec2 meshPos;
uniform int size;

void main() {
    vec2 newPos=(position+meshPos)/size;
    gl_Position=vec4(newPos*2-1,0,1);
}
