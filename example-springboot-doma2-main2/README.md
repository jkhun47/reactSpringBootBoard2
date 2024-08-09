# example-springboot-doma2

## Overview

「Spring Boot」+「Doma2」を使用して、複数データソースを使用してデータベースへアクセスするためのサンプルプロジェクトです。


## Requirement

- Java Development Kit（JDK）：11+
- データベースサーバ：任意

サンプルプロジェクトでは、次のデータベースサーバを使用しています。

- PostgreSQL：12.4

## Install

```sh
$ git clone https://github.com/fumidzuki/example-springboot-doma2.git
```

## Usage

### ビルド

次のコマンドでビルドを実行します。

```sh
$ gradlew clean build
```

### 起動方法

データベースサーバが起動していることを確認してから、次のコマンドで実行します。

```sh
$ gradlew bootRun
```

データベースサーバへ接続できないと起動時にエラーが発生します。

## Licence

[MIT](https://github.com/fumidzuki/example-springboot-doma2/blob/main/LICENSE)

## Author

[fumidzuki](https://fumidzuki.com)
