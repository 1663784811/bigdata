import numpy as np
from scipy.spatial import distance

# 加载两张人脸图像
image1 = np.load("1.jpg")
image2 = np.load("2.png")

# 计算直方图
hist1, _ = np.histogram(image1.ravel(), bins=256, range=[0, 256])
hist2, _ = np.histogram(image2.ravel(), bins=256, range=[0, 256])

# 归一化直方图
hist1 = hist1.astype("float")
hist2 = hist2.astype("float")
hist1 /= hist1.sum()
hist2 /= hist2.sum()

# 计算巴氏距离
similarity = 1 - distance.bhattacharyya(hist1, hist2)

# 显示结果
if similarity > 0.5:  # 设置相似度阈值
    print("这两张人脸相似")
else:
    print("这两张人脸不相似")
