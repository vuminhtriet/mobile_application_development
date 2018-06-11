import React from 'react';
import { View, Text, StyleSheet, Button, Image } from 'react-native';
import { StackNavigator, TabNavigator } from 'react-navigation';
import { SearchBar } from 'react-native-elements';
import Icon from 'react-native-vector-icons/FontAwesome';

const myIcon = (<Icon name="rocket" size={40} color="#900" />)

class HomeScreen extends React.Component {
  static navigationOptions = {
    tabBarLabel: '',
    tabBarIcon: ({ tintColor }) => (
      <Image
        source={require('./images/home.png')}
        style={[styles.tabicon, { tintColor: 'white' }]}
      />
    ),
  };

  render() {
    return (
      <View style={{flex: 1, flexDirection: 'row'}}>
        <View style={{flex: 5, height: 60}}>
          <SearchBar
            containerStyle={{backgroundColor:'#3396FF', borderWidth: 0, borderColor: '#3396FF'}}
            inputStyle={{color:'#3396FF', backgroundColor: 'white'}}
            //placeholderTextColor='#fff'
            showLoading
            platform="android"
            cancelIcon={{ type: 'font-awesome', name: 'chevron-left' }}
            showIcon = {myIcon}
            placeholder='Search' />
        </View>
        <View style={{flex: 1, height: 58, justifyContent: 'center', alignItems: 'center', backgroundColor:'#3396FF'}}> 
          <Image
            source={require('./images/shopping.png')}
            style={[styles.icon, { tintColor: 'white' }]}
          />
        </View>
      </View>
    );
  }
}

class UserScreen extends React.Component {
  static navigationOptions = {
    tabBarLabel: '',
    tabBarIcon: ({ tintColor }) => (
      <Image
        source={require('./images/user.png')}
        style={[styles.tabicon, { tintColor: 'white' }]}
      />
    ),
  };

  render() {
    return (
      <Button
        onPress={() => this.props.navigation.goBack()}
        title="Go back home"
      />
    );
  }
}

export default App;

const App = TabNavigator({
  Home: {
    screen: HomeScreen,
  },
  Notifications: {
    screen: UserScreen,
  },
}, {
    tabBarPosition: 'bottom',
    animationEnabled: true,
    tabBarOptions: {
      showIcon: true,
      showLabel: false,
    },
  });

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
  icon: {
    width: 45,
    height: 45,
  },
  tabicon: {
    width: 26,
    height: 26,
  },
});