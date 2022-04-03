package ca.rttv.malum.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;

import java.awt.*;
import java.util.Optional;
import java.util.function.Supplier;

public final class RenderHelper {
    public static final int FULL_BRIGHT = 15728880;

    public static Shader getShader(RenderLayer type) {
        if (type instanceof RenderLayer.MultiPhase multiPhase) {
            Optional<Supplier<Shader>> shader = multiPhase.phases.shader.supplier;
            if (shader.isPresent()) {
                return shader.get().get();
            }
        }
        return null;
    }

    public static void blit(MatrixStack matrices, ShaderHolder shader, int x, int y, double w, double h, float u, float v, float xCanvasSize, float yCanvasSize) {
        innerBlit(matrices, shader, x, y, w, h, u / xCanvasSize, v / yCanvasSize, (float) w / xCanvasSize, (float) h / yCanvasSize);
    }

    public static void blit(MatrixStack matrices, ShaderHolder shader, int x, int y, double w, double h, float u, float v, float uw, float vh, float xCanvasSize, float yCanvasSize) {
        innerBlit(matrices, shader, x, y, w, h, u / xCanvasSize, v / yCanvasSize, uw / xCanvasSize, vh / yCanvasSize);
    }

    public static void blit(MatrixStack matrices, ShaderHolder shader, int x, int y, double w, double h, float u, float v, float canvasSize) {
        innerBlit(matrices, shader, x, y, w, h, u / canvasSize, v / canvasSize, (float) (x + w) / canvasSize, (float) (y + h) / canvasSize);
    }

    public static void blit(MatrixStack matrices, ShaderHolder shader, int x, int y, double w, double h, float u, float v, float uw, float vh, float canvasSize) {
        innerBlit(matrices, shader, x, y, w, h, u / canvasSize, v / canvasSize, uw / canvasSize, vh / canvasSize);
    }

    public static void blit(MatrixStack matrices, ShaderHolder shader, int x, int y, double w, double h, float r, float g, float b, float a, float u, float v, float xCanvasSize, float yCanvasSize) {
        innerBlit(matrices, shader, x, y, w, h, r, g, b, a, u / xCanvasSize, v / yCanvasSize, (float) w / xCanvasSize, (float) h / yCanvasSize);
    }

    public static void blit(MatrixStack matrices, ShaderHolder shader, int x, int y, double w, double h, float r, float g, float b, float a, float u, float v, float uw, float vh, float xCanvasSize, float yCanvasSize) {
        innerBlit(matrices, shader, x, y, w, h, r, g, b, a, u / xCanvasSize, v / yCanvasSize, uw / xCanvasSize, vh / yCanvasSize);
    }

    public static void blit(MatrixStack matrices, ShaderHolder shader, int x, int y, double w, double h, float r, float g, float b, float a, float u, float v, float canvasSize) {
        innerBlit(matrices, shader, x, y, w, h, r, g, b, a, u / canvasSize, v / canvasSize, (float) w / canvasSize, (float) h / canvasSize);
    }

    public static void blit(MatrixStack matrices, ShaderHolder shader, int x, int y, double w, double h, float r, float g, float b, float a, float u, float v, float uw, float vh, float canvasSize) {
        innerBlit(matrices, shader, x, y, w, h, r, g, b, a, u / canvasSize, v / canvasSize, uw / canvasSize, vh / canvasSize);
    }

    public static void innerBlit(MatrixStack matrices, ShaderHolder shader, int x, int y, double w, double h, float r, float g, float b, float a, float u, float v, float uw, float vh) {
        Matrix4f last = matrices.peek().getModel();
        RenderSystem.setShader(shader.getInstance());
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
        bufferbuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR_TEXTURE);
        bufferbuilder.vertex(last, (float) x, (float) y + (float) h, 0).color(r, g, b, a).texture(u, v + vh).next();
        bufferbuilder.vertex(last, (float) x + (float) w, (float) y + (float) h, 0).color(r, g, b, a).texture(u + uw, v + vh).next();
        bufferbuilder.vertex(last, (float) x + (float) w, (float) y, 0).color(r, g, b, a).texture(u + uw, v).next();
        bufferbuilder.vertex(last, (float) x, (float) y, 0).color(r, g, b, a).texture(u, v).next();
        bufferbuilder.end();
        BufferRenderer.draw(bufferbuilder);
    }

    public static void innerBlit(MatrixStack stack, ShaderHolder shader, int x, int y, double w, double h, float u, float v, float uw, float vh) {
        Matrix4f last = stack.peek().getModel();
        RenderSystem.setShader(shader.getInstance());
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
        bufferbuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
        bufferbuilder.vertex(last, (float) x, (float) y + (float) h, 0).texture(u, v + vh).next();
        bufferbuilder.vertex(last, (float) x + (float) w, (float) y + (float) h, 0).texture(u + uw, v + vh).next();
        bufferbuilder.vertex(last, (float) x + (float) w, (float) y, 0).texture(u + uw, v).next();
        bufferbuilder.vertex(last, (float) x, (float) y, 0).texture(u, v).next();
        bufferbuilder.end();
        BufferRenderer.draw(bufferbuilder);
    }

    public static void blit(MatrixStack matrices, int x, int y, double w, double h, float u, float v, float xCanvasSize, float yCanvasSize) {
        innerBlit(matrices, x, y, w, h, u / xCanvasSize, v / yCanvasSize, (float) w / xCanvasSize, (float) h / yCanvasSize);
    }

    public static void blit(MatrixStack matrices, int x, int y, double w, double h, float u, float v, float uw, float vh, float xCanvasSize, float yCanvasSize) {
        innerBlit(matrices, x, y, w, h, u / xCanvasSize, v / yCanvasSize, uw / xCanvasSize, vh / yCanvasSize);
    }

    public static void blit(MatrixStack matrices, int x, int y, double w, double h, float u, float v, float canvasSize) {
        innerBlit(matrices, x, y, w, h, u / canvasSize, v / canvasSize, (float) w / canvasSize, (float) h / canvasSize);
    }

    public static void blit(MatrixStack matrices, int x, int y, double w, double h, float u, float v, float uw, float vh, float canvasSize) {
        innerBlit(matrices, x, y, w, h, u / canvasSize, v / canvasSize, uw / canvasSize, vh / canvasSize);
    }

    public static void blit(MatrixStack matrices, int x, int y, double w, double h, float r, float g, float b, float a, float u, float v, float xCanvasSize, float yCanvasSize) {
        innerBlit(matrices, x, y, w, h, r, g, b, a, u / xCanvasSize, v / yCanvasSize, (float) w / xCanvasSize, (float) h / yCanvasSize);
    }

    public static void blit(MatrixStack matrices, int x, int y, double w, double h, float r, float g, float b, float a, float u, float v, float uw, float vh, float xCanvasSize, float yCanvasSize) {
        innerBlit(matrices, x, y, w, h, r, g, b, a, u / xCanvasSize, v / yCanvasSize, uw / xCanvasSize, vh / yCanvasSize);
    }

    public static void blit(MatrixStack matrices, int x, int y, double w, double h, float r, float g, float b, float a, float u, float v, float canvasSize) {
        innerBlit(matrices, x, y, w, h, r, g, b, a, u / canvasSize, v / canvasSize, (float) w / canvasSize, (float) h / canvasSize);
    }

    public static void blit(MatrixStack matrices, int x, int y, double w, double h, float r, float g, float b, float a, float u, float v, float uw, float vh, float canvasSize) {
        innerBlit(matrices, x, y, w, h, r, g, b, a, u / canvasSize, v / canvasSize, uw / canvasSize, vh / canvasSize);
    }

    public static void innerBlit(MatrixStack matrices, int x, int y, double w, double h, float r, float g, float b, float a, float u, float v, float uw, float vh) {
        Matrix4f last = matrices.peek().getModel();
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
        bufferbuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR_TEXTURE);
        bufferbuilder.vertex(last, (float) x, (float) y + (float) h, 0).color(r, g, b, a).texture(u, v + vh).next();
        bufferbuilder.vertex(last, (float) x + (float) w, (float) y + (float) h, 0).color(r, g, b, a).texture(u + uw, v + vh).next();
        bufferbuilder.vertex(last, (float) x + (float) w, (float) y, 0).color(r, g, b, a).texture(u + uw, v).next();
        bufferbuilder.vertex(last, (float) x, (float) y, 0).color(r, g, b, a).texture(u, v).next();
        bufferbuilder.end();
        BufferRenderer.draw(bufferbuilder);
    }

    public static void innerBlit(MatrixStack matrices, int x, int y, double w, double h, float u, float v, float uw, float vh) {
        Matrix4f last = matrices.peek().getModel();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
        bufferbuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
        bufferbuilder.vertex(last, (float) x, (float) y + (float) h, 0).texture(u, v + vh).next();
        bufferbuilder.vertex(last, (float) x + (float) w, (float) y + (float) h, 0).texture(u + uw, v + vh).next();
        bufferbuilder.vertex(last, (float) x + (float) w, (float) y, 0).texture(u + uw, v).next();
        bufferbuilder.vertex(last, (float) x, (float) y, 0).texture(u, v).next();
        bufferbuilder.end();
        BufferRenderer.draw(bufferbuilder);
    }

    public static VertexBuilder create() {
        return new VertexBuilder();
    }

    public static void vertexPos(VertexConsumer vertexConsumer, Matrix4f last, float x, float y, float z) {
        vertexConsumer.vertex(last, x, y, z).next();
    }

    public static void vertexPosUV(VertexConsumer vertexConsumer, Matrix4f last, float x, float y, float z, float u, float v) {
        vertexConsumer.vertex(last, x, y, z).texture(u, v).next();
    }

    public static void vertexPosUVLight(VertexConsumer vertexConsumer, Matrix4f last, float x, float y, float z, float u, float v, int light) {
        vertexConsumer.vertex(last, x, y, z).texture(u, v).light(light).next();
    }

    public static void vertexPosColor(VertexConsumer vertexConsumer, Matrix4f last, float x, float y, float z, float r, float g, float b, float a) {
        vertexConsumer.vertex(last, x, y, z).color(r, g, b, a).next();
    }

    public static void vertexPosColorUV(VertexConsumer vertexConsumer, Matrix4f last, float x, float y, float z, float r, float g, float b, float a, float u, float v) {
        vertexConsumer.vertex(last, x, y, z).color(r, g, b, a).texture(u, v).next();
    }

    public static void vertexPosColorUVLight(VertexConsumer vertexConsumer, Matrix4f last, float x, float y, float z, float r, float g, float b, float a, float u, float v, int light) {
        vertexConsumer.vertex(last, x, y, z).color(r, g, b, a).texture(u, v).light(light).next();
    }

    public static Vec3f parametricSphere(float u, float v, float r) {
        return new Vec3f(MathHelper.cos(u) * MathHelper.sin(v) * r, MathHelper.cos(v) * r, MathHelper.sin(u) * MathHelper.sin(v) * r);
    }

    public static class VertexBuilder {
        float r = 1, g = 1, b = 1, a = 1;
        float xOffset = 0, yOffset = 0, zOffset = 0;
        int light = FULL_BRIGHT;
        float u0 = 0, v0 = 0, u1 = 1, v1 = 1;


        public VertexBuilder setColor(Color color) {
            return setColor(color.getRed(), color.getGreen(), color.getBlue());
        }

        public VertexBuilder setColor(Color color, float a) {
            return setColor(color).setAlpha(a);
        }

        public VertexBuilder setColor(float r, float g, float b, float a) {
            return setColor(r, g, b).setAlpha(a);
        }

        public VertexBuilder setColor(float r, float g, float b) {
            this.r = r / 255f;
            this.g = g / 255f;
            this.b = b / 255f;
            return this;
        }

        public VertexBuilder setAlpha(float a) {
            this.a = a;
            return this;
        }

        public VertexBuilder setOffset(float xOffset, float yOffset, float zOffset) {
            this.xOffset = xOffset;
            this.yOffset = yOffset;
            this.zOffset = zOffset;
            return this;
        }

        public VertexBuilder setLight(int light) {
            this.light = light;
            return this;
        }

        public VertexBuilder setUV(float u0, float v0, float u1, float v1) {
            this.u0 = u0;
            this.v0 = v0;
            this.u1 = u1;
            this.v1 = v1;
            return this;
        }

        public VertexBuilder renderTriangle(VertexConsumer vertexConsumer, MatrixStack matrices, float size) {
            return renderTriangle(vertexConsumer, matrices, size, size);
        }

        public VertexBuilder renderTriangle(VertexConsumer vertexConsumer, MatrixStack matrices, float width, float height) {
            Matrix4f last = matrices.peek().getModel();

            vertexPosColorUVLight(vertexConsumer, last, -width, -height, 0, r, g, b, a, 0, 1, light);
            vertexPosColorUVLight(vertexConsumer, last, width, -height, 0, r, g, b, a, 1, 1, light);
            vertexPosColorUVLight(vertexConsumer, last, 0, height, 0, r, g, b, a, 0.5f, 0, light);
            return this;
        }

        public VertexBuilder renderBeam(VertexConsumer vertexConsumer, MatrixStack matrices, Vec3d start, Vec3d end, float width) {
            MinecraftClient minecraft = MinecraftClient.getInstance();
            start.add(xOffset, yOffset, zOffset);
            end.add(xOffset, yOffset, zOffset);
            matrices.translate(-start.x, -start.y, -start.z);
            Vec3d cameraPosition = minecraft.getBlockEntityRenderDispatcher().camera.getPos();
            Vec3d delta = end.subtract(start);
            Vec3d normal = start.subtract(cameraPosition).crossProduct(delta).normalize().multiply(width / 2f, width / 2f, width / 2f);
            Matrix4f last = matrices.peek().getModel();
            Vec3d[] positions = new Vec3d[]{start.subtract(normal), start.add(normal), end.add(normal), end.subtract(normal)};
            vertexPosColorUVLight(vertexConsumer, last, (float) positions[0].x, (float) positions[0].y, (float) positions[0].z, r, g, b, a, u0, v1, light);
            vertexPosColorUVLight(vertexConsumer, last, (float) positions[1].x, (float) positions[1].y, (float) positions[1].z, r, g, b, a, u1, v1, light);
            vertexPosColorUVLight(vertexConsumer, last, (float) positions[2].x, (float) positions[2].y, (float) positions[2].z, r, g, b, a, u1, v0, light);
            vertexPosColorUVLight(vertexConsumer, last, (float) positions[3].x, (float) positions[3].y, (float) positions[3].z, r, g, b, a, u0, v0, light);
            matrices.translate(start.x, start.y, start.z);
            return this;
        }

        public VertexBuilder renderQuad(VertexConsumer vertexConsumer, MatrixStack matrices, float size) {
            return renderQuad(vertexConsumer, matrices, size, size);
        }

        public VertexBuilder renderQuad(VertexConsumer vertexConsumer, MatrixStack matrices, float width, float height) {
            Matrix4f last = matrices.peek().getModel();
            matrices.translate(xOffset, yOffset, zOffset);
            Vec3d[] positions = new Vec3d[]{new Vec3d(-width, -height, 0), new Vec3d(width, -height, 0), new Vec3d(width, height, 0), new Vec3d(-width, height, 0)};
            vertexPosColorUVLight(vertexConsumer, last, (float) positions[0].x, (float) positions[0].y, (float) positions[0].z, r, g, b, a, u0, v1, light);
            vertexPosColorUVLight(vertexConsumer, last, (float) positions[1].x, (float) positions[1].y, (float) positions[1].z, r, g, b, a, u1, v1, light);
            vertexPosColorUVLight(vertexConsumer, last, (float) positions[2].x, (float) positions[2].y, (float) positions[2].z, r, g, b, a, u1, v0, light);
            vertexPosColorUVLight(vertexConsumer, last, (float) positions[3].x, (float) positions[3].y, (float) positions[3].z, r, g, b, a, u0, v0, light);
            matrices.translate(-xOffset, -yOffset, -zOffset);
            return this;
        }

        public VertexBuilder renderSphere(VertexConsumer vertexConsumer, MatrixStack matrices, float radius, int longs, int lats) {
            Matrix4f last = matrices.peek().getModel();
            float startU = 0;
            float startV = 0;
            float endU = MathHelper.PI * 2;
            float endV = MathHelper.PI;
            float stepU = (endU - startU) / longs;
            float stepV = (endV - startV) / lats;
            for (int i = 0; i < longs; ++i) {
                // U-points
                for (int j = 0; j < lats; ++j) {
                    // V-points
                    float u = i * stepU + startU;
                    float v = j * stepV + startV;
                    float un = (i + 1 == longs) ? endU : (i + 1) * stepU + startU;
                    float vn = (j + 1 == lats) ? endV : (j + 1) * stepV + startV;
                    Vec3f p0 = parametricSphere(u, v, radius);
                    Vec3f p1 = parametricSphere(u, vn, radius);
                    Vec3f p2 = parametricSphere(un, v, radius);
                    Vec3f p3 = parametricSphere(un, vn, radius);

                    float textureU = u / endU * radius;
                    float textureV = v / endV * radius;
                    float textureUN = un / endU * radius;
                    float textureVN = vn / endV * radius;
                    vertexPosColorUVLight(vertexConsumer, last, p0.getX(), p0.getY(), p0.getZ(), r, g, b, a, textureU, textureV, light);
                    vertexPosColorUVLight(vertexConsumer, last, p2.getX(), p2.getY(), p2.getZ(), r, g, b, a, textureUN, textureV, light);
                    vertexPosColorUVLight(vertexConsumer, last, p1.getX(), p1.getY(), p1.getZ(), r, g, b, a, textureU, textureVN, light);

                    vertexPosColorUVLight(vertexConsumer, last, p3.getX(), p3.getY(), p3.getZ(), r, g, b, a, textureUN, textureVN, light);
                    vertexPosColorUVLight(vertexConsumer, last, p1.getX(), p1.getY(), p1.getZ(), r, g, b, a, textureU, textureVN, light);
                    vertexPosColorUVLight(vertexConsumer, last, p2.getX(), p2.getY(), p2.getZ(), r, g, b, a, textureUN, textureV, light);
                }
            }
            return this;
        }
    }
}