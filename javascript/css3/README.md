#相关属性的讨论

##1. transform-origin属性
这个属性用于设置的:元素旋转的支点中心

e.g:
一个div设置了长:400,宽200后,加上以下属性
```
  transform-origin: center bottom;
```

再添加上:

```
    transform: rotate(45deg);
```

它让旋转


那么它会以(200,200)这个作标点为中心,然后旋转45度