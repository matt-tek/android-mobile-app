import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { TodoModuleModule } from './todo-module/todo-module.module';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ConfigModule, ConfigService } from '@nestjs/config';

@Module({
  imports: [
    TodoModuleModule,
    ConfigModule.forRoot({
      envFilePath: '.env',
      isGlobal: true,
    }),
    TypeOrmModule.forRootAsync({
      imports: [ConfigModule],
      useFactory: (configService: ConfigService) => ({
        type: 'mariadb',
        host: configService.get('MYSQL_HOST'),
        port: parseInt(configService.get('MYSQL_TCP_PORT')),
        username: configService.get('MYSQL_USER'),
        password: configService.get('MYSQL_ROOT_PASSWORD'),
        database: configService.get('MYSQL_TODO_DATABASE'),
        entities: ['dist/**/*.entity.js'],
        synchronize: true,
      }),
      inject: [ConfigService],
    }),
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
