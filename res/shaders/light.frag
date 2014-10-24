varying vec3 varyingNormal;
varying vec4 varyingVertex;
uniform sampler2D texture;

void main() {
    vec3 vertexPosition = (gl_ModelViewMatrix * varyingVertex).xyz;
    vec3 lightPosition = gl_LightSource[0].position.xyz / gl_LightSource[0].position.w;
    vec3 surfaceNormal = normalize((gl_NormalMatrix * varyingNormal).xyz);
    vec3 lightDirection = normalize(lightPosition - vertexPosition);
    vec3 vertexDirection = normalize(-vertexPosition);
    vec3 halfVector = normalize(vertexDirection + lightDirection);
    float diffuseLightIntensity = max(0, dot(lightDirection, surfaceNormal));
    vec4 ambient = gl_LightSource[0].ambient * gl_FrontMaterial.ambient;
    vec4 diffuse = gl_LightSource[0].diffuse * gl_FrontMaterial.diffuse * diffuseLightIntensity;
    float specularLightIntensity = 0f;
    vec4 specular = vec4(0, 0, 0, 1);
    if (diffuseLightIntensity > 0f) {
        specularLightIntensity = pow(dot(halfVector, surfaceNormal), gl_FrontMaterial.shininess);
        specular = gl_LightSource[0].specular * gl_FrontMaterial.specular * specularLightIntensity;
    }
    gl_FragColor = (ambient + diffuse) * gl_Color;
    //gl_FragColor *= texture2D(texture, gl_TexCoord[0].st);
    gl_FragColor += specular;
}