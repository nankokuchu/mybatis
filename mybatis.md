- properties（属性），来引入外部properties配置文件的内容

  `**resource**`：引入类路径下的资源

  `**url**`：引入网络路径或者磁盘路径下的资源

- **设置（`settings`）这是 `MyBatis` 中极为重要的调整设置，它们会改变 `MyBatis` 的运行时行为。 下表描述了设置中各项设置的含义、默认值等。**
- **类型别名（`typeAliases`）类型别名可为 Java 类型设置一个缩写名字。 它仅用于 XML 配置，意在降低冗余的全限定类名书写。**

  `**typeAlias标签` `type属性` `alias属性` `pakage标签` `name属性`**

    ```xml
    <typeAliases>
            <!--
                typeAlias标签，为某一个类型起别名
                type属性：要起别名的类型名
                alias属性：要起的别名，不显示的写的时候，默认为 类名的小写
            -->
            <!--<typeAlias type="com.atzzazz.mybatis.bean.Employee" alias="employee"/>-->
        <!--    
            一次性为一个包下所有的类起别名
            package标签:
            name属性:全包名,此包下的所有类的别名为,小写的类名
        -->
            <package name="com.atzzazz.mybatis.bean"/>
        </typeAliases>
    ```

# mybatis参数处理

# 单个参数

<aside>
💡 单个参数的时候，`**mybatis**` 不做任何处理

- #{参数名}：取出参数
</aside>

# 多个参数

<aside>
💡 多个参数的时候， `**mybatis**` 会做特殊处理

</aside>

- 多个参数，会做封装成一个
   - `**#{}`** 就是从 `**map**` 中获取指定的 `**key**` 的值
      - 在默认情况下，`**key**` ： `**param1**` ... **`paramN`**
      - 在默认情况下，`**value**`： 传的参数值
- 命名参数：明确指定疯转参数是**`map`**的`**key**`值
   - `**@param("别名")` 注解**
   - `Users selectUsersByIdAndUserName*(*@Param*(*"id"*)* Integer id, @Param*(*"userName"*)* String userName*)*;`
- 如果多个参数正好是我们业务逻辑模型，我们可以直接传入`**pojo**`
- 如果多个参数没有对应的pojo，那么可以直接传入 **`map`**
   - 此时的`**#{}**` 就是 `**map**` 当中的 `**key**`
- ‼️如果传入的参数是 `**Collection(List, Set)类型**`的或者是数组的时候，也会特殊处理，也是会封装到`**map**`当中。但是`**map**`当中的**`key`**是固定的。
   - key ： Collection 的时候是`**#(collection)**`
   - key ： List 的时候是 **`#(list)`**
   - key ： 数组的时候是 `**#(array)**`

# 参数的取值

- `**#{}` 参数可以使用，相当于预编译，占位符`?`**
- `**${}` jdbc不支持，但是想动态的取值的时候使用。比如，表名，排序等**

# CRUD 操作

1. mybatis允许增删改直接定义以下类型返回值，直接在接口的方法的返回值设置即可
    1. Integer
    2. Long
    3. Boolean
2. 我们需要手动提交数据

# 自增主键值的获取

`useGeneratedKeys="true"` 属性； **`insert` 标签内**

`keyProperty="id"` 指定对应的逐渐属性，也就是mybatis获取到主键值以后，将这个值封装给javaBean的哪个属性里

# 查询结果为List集合

<aside>
💡 返回类型为 List<>集合的时候，`**resultType=LIst集合**`里的元素封装的类型
`**List<Users> ⇒ resultType= Users的全类名**`

</aside>

```java
List<Users> getUsersByUserName(String userName);
```

```xml
    <select id="getUsersByUserName" resultType="com.atzzazz.pojo.Users">
        select * from mybatis.users where user_name like #{userName}
    </select>
```

#MyBatis-缓存机制（Cache）
# 一级缓存

- **一级缓存**：（本地缓存）：sqlSession级别的缓存。一级缓存是一直开启的
   - 与数据库同一次会话期间查询到的数据会放在本地缓存当中
   - 以后如果需要获取相同的数据，直接从缓存中拿，没必要再去查询数据库

   - 一级缓存失效的情况（没有使用到当前一级缓存的情况，效果就是，还需要在向数据库发出查询）
      1. sqlSession不同。
      2. sqlSession相同，查询条件不同（当前一级缓存中还没有这个数据）
      3. sqlSession相同，两次查询之间执行了怎删改操作（这次增删改可能对当前数据有影响）
      4. sqlSession相同，手动清楚了一级缓存（缓存清空）`**session.clearCache();**`

  [一级缓存的举例](https://www.notion.so/3339b14aee3f4243b4749c6586f7f5d1)


# 二级缓存

- **二级缓存**：（全集缓存）：基于`**namespace**`级别的缓存；一个`**namespace**`对应一个二级缓存
   - 工作机制：
      1. 一个会话，查询一条数据，这个数据会被放在当前会话的一级缓存中
      2. 如果会话关闭，一级缓存中的数据会被保存到二级缓存中，新的会话查询信息，就可以参照二级缓存
      3. 不同`**namespace**`查出的数据会放在自己对应的缓存中（mapper下）

   - 二级缓存的使用步骤
      1. 开启全局二级缓存设置：
         - `**<setting name="cacheEnabled" value="true"/>**` 默认值是`**true**`，但是建议显示的开启
      2. 在`**mapper.xml**`中配置使用二级缓存
         - `<**cache**></**cache**>` 标签
            - `eviction=""属性`  缓存的回收策略，`**LRU**`(默认)，`**FIFO**`，`**SOFT**`，`**WEAK**`
            - `readOnly=""属性` 是否只读，
               - `**true`** 只读，不安全，速度快
               - `**false**` 非只读（默认），会利用序列化&反序列技术，安全，速度慢。
            - `flushInterval=""属性` 缓存刷新间隔，以毫秒数来指定，默认不清空
            - `size=""属性` 缓存存放多少元素
            - `type=""属性` 指定自定义缓存的全类名，自定义缓存，实现`**Cache**`接口即可
      3. `**readOnly属性**`默认是`**false**`，所以我们的`**POJO**`需要实现`**Serializable接口**`

# **和缓存有关的设置/属性**

- **和缓存有关的设置/属性**
   - `**<setting name="cacheEnabled" value="false"/>` `false`**的时候，关闭二级缓存，但是一级缓存没有关闭
   - 每个`**select标签**`都有**`useCache=""属性`**，默认是`**true**`，`**false**`关闭二级缓存，一级缓存不会关闭
   - 每个`**增删改标签**`都有`**flushCache=""属性**` ，默认是`**true**`，增删改操作完以后清除`**一级二级所有缓存**`
      - 每个`**select标签也**`有`**flushCache=""属性` ，**但是默认是`false` ，所以可以使用缓存功能。
   - `**session.clearCache();`** 只清楚当前session的一级缓存
   - `**<setting name="localCacheScope" value=""/>`**
      - `**value=SESSION**`(默认SESSION)，启用一级缓存，当前会话的所有数据，保存到到当前Session当中
      - `**value=STATEMENT**`，关闭一级缓存