#!/usr/bin/env bash

function newClient() {
    echo ""
    echo "Tell me a name for the client."
    echo "The name must consist of alphanumeric character. It may also include an underscore or a dash."
    until [[ $CLIENT =~ ^[a-zA-Z0-9_-]+$ ]]; do
        read -rp "Client name: " -e CLIENT
    done
    
    echo ""
    echo "Do you want to protect the configuration file with a password?"
    echo "(e.g. encrypt the private key with a password)"
    echo "   1) Add a passwordless client"
    echo "   2) Use a password for the client"
    until [[ $PASS =~ ^[1-2]$ ]]; do
        read -rp "Select an option [1-2]: " -e -i 1 PASS
    done


    echo ""
    echo "The configuration file has been written to /somedir/abcd.ovpn."
    echo "Download the .ovpn file and import it in your OpenVPN client."
    exit 0

}

    echo "Welcome to OpenVPN-install!"
    echo "The git repository is available at: https://github.com/angristan/openvpn-install"
    echo ""
    echo "It looks like OpenVPN is already installed."
    echo ""
    echo "What do you want to do?"
    echo "   1) Add a new user"
    echo "   2) Revoke existing user"
    echo "   3) Remove OpenVPN"
    echo "   4) Exit"
    until [[ $MENU_OPTION =~ ^[1-4]$ ]]; do
        read -rp "Select an option [1-4]: " MENU_OPTION
    done


    case $MENU_OPTION in
    1)
        newClient
        ;;
    2)
        revokeClient
        ;;
    3)
        removeOpenVPN
        ;;
    4)
        exit 0
        ;;
    esac


