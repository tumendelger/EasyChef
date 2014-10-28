EasyChef
========

EasyChef Kitchen application for EasySystems

Project structure:
    1. Package structure
        - easychef
            -- data                                         //Classes related with DB
                -- exceptions (Package)                     //Exception classes for easychef.data package classes
                    -- OrderDetailNotFoundException.java
                    -- UserNotFoundException.java
                -- utils (Package)                          //Reusable utility classes 
                    -- DateTimeUtils
                    -- SystemSettings
                -- Constants.java
                -- DBConnector.java
                -- DeliveryStats.java
                -- LocalDBSchema.sql
                -- MessageType.java
                -- OrderDetail.java
                -- PrintOrder.java
                -- RemoteDBConnector.java
                -- RemoteDBSchema.sql
                -- User.java
            -- main (Package)                               //Main package
                -- EasyChef.java
            -- net (Package)                                //Network related operations classes
                -- ClientHandler.java
                -- ClientMessageHandler.java
                -- Message.java
                -- MessageSender.java
                -- OrderHandler.java
                -- TCPConnector.java
                -- TCPListener.java
            -- remote (Package)                             //Remote DB Fetch and Sync related classes
                -- DBSyncSQLs.java
                -- DBSyncer.java
                -- RemoteService.java
                -- SiteIdNotFoundException.java
                -- SyncCards.java
                -- SyncMenus.java
                -- SynsSystemDate.java
            -- resources (Package)                          //Resources for GUI
                --                                          //Images and other sources
            -- ui (Package)                                 //GUI definition classes and FXML files
                -- Login.fxml
                -- login.css
                -- LoginController.java
                -- LoginManager.java
                -- Main.fxml
                -- Main.css
                -- MainController.java
                -- SplashScreen.fxml
                -- splashscreen.css
                -- SplashScreenController.java
                -- WaitTimeUpdater.java
    
    2. 







